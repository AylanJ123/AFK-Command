package com.aylanj123.afkcommand.eventhandler;

import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.Config;
import com.aylanj123.afkcommand.LangKeys;
import com.aylanj123.afkcommand.afkstate.capability.PlayerAFKStateProvider;
import com.aylanj123.afkcommand.afkstate.capability.StateSource;
import com.aylanj123.afkcommand.networking.PacketHandler;
import com.aylanj123.afkcommand.networking.packets.IdleConfigS2CPacket;
import com.aylanj123.afkcommand.registry.CapabilitiesRegistry;
import com.aylanj123.afkcommand.registry.CommandRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class ServerEventHandler {

    public static class ServerForgeEvents {

        @SubscribeEvent
        static void registerCommands(RegisterCommandsEvent event) {
            AFKCommandMod.LOGGER.info("Setting up the commands");
            CommandRegistry.register(event.getDispatcher());
        }

        @SubscribeEvent
        static void registerCapabilities(RegisterCapabilitiesEvent event) {
            AFKCommandMod.LOGGER.info("Setting up the capabilities");
            CapabilitiesRegistry.register(event);
        }

        @SubscribeEvent
        static void attachCaps(AttachCapabilitiesEvent<Entity> event) {
            Entity player = event.getObject();
            if (!(player instanceof Player) || player.getCapability(PlayerAFKStateProvider.AFK_STATE).isPresent()) return;
            event.addCapability(new ResourceLocation(AFKCommandMod.MODID, "properties"), new PlayerAFKStateProvider());
        }

        @SubscribeEvent
        static void playerCloned(PlayerEvent.Clone event) {
            if (!event.isWasDeath()) return;
            event.getOriginal().getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(old -> {
                event.getOriginal().getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(newCap -> {
                    newCap.copyFrom(old);
                });
            });
        }

        @SubscribeEvent
        static void serverSetUp(ServerStartingEvent event) {
            AFKCommandMod.LOGGER.info("Setting up the server");
            Config.serverSidedLoad();
        }

        @SubscribeEvent
        static void playerTicked(TickEvent.PlayerTickEvent event) {
            if (event.side == LogicalSide.CLIENT) return;
            event.player.getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(cap -> {
                ServerPlayer player = ((ServerPlayer) event.player);
                if (player.hasDisconnected()) return;
                if (cap.isAFK()) cap.addTick();
                if (Config.timeToKick != -1)
                    if (cap.timeAFK() > Config.timeToKick)
                        player.connection.disconnect(Component.translatable(LangKeys.KICK_IDLE.key(), Config.timeToKick / 20));
            });
        }

        @SubscribeEvent
        static void entityJoined(EntityJoinLevelEvent event) {
            Entity player = event.getEntity();
            if (!(player instanceof ServerPlayer)) return;
            player.getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(cap -> {
                if (Config.afkOnLogin) cap.putAFK(StateSource.LOGIN_APPLIED, (ServerPlayer) player);
            });
            PacketHandler.sendPlayer(new IdleConfigS2CPacket(Config.timeToSendAFK), (ServerPlayer) player);
        }

        @SubscribeEvent
        static void playerAttack(LivingDamageEvent event) {
            if (!Config.invinciblePlayers) return;
            boolean towardsPlayer = event.getEntity() instanceof Player && event.getSource().getEntity() instanceof LivingEntity;
            boolean fromPlayer = event.getSource().getEntity() instanceof Player && (event.getEntity() instanceof Enemy || event.getEntity() instanceof NeutralMob || event.getEntity() instanceof Player);
            if (towardsPlayer) {
                ServerPlayer player = (ServerPlayer) event.getEntity();
                player.getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(cap -> cap.setLastTimeCombat(player));
            }
            if (fromPlayer) {
                ServerPlayer player = (ServerPlayer) event.getSource().getEntity();
                player.getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(cap -> cap.setLastTimeCombat(player));
            }
        }

    }


}