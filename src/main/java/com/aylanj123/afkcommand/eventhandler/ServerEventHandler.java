package com.aylanj123.afkcommand.eventhandler;
import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.afkstate.capability.PlayerAFKStateProvider;
import com.aylanj123.afkcommand.registry.CapabilitiesRegistry;
import com.aylanj123.afkcommand.registry.CommandRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class ServerEventHandler {

    @SubscribeEvent
    void registerCommands(RegisterCommandsEvent event) {
        AFKCommandMod.LOGGER.info("Setting up the commands");
        CommandRegistry.register(event.getDispatcher());
    }

    @SubscribeEvent
    void registerCapabilities(RegisterCapabilitiesEvent event) {
        AFKCommandMod.LOGGER.info("Setting up the capabilities");
        CapabilitiesRegistry.register(event);
    }

    @SubscribeEvent
    void attachCaps(AttachCapabilitiesEvent<Entity> event) {
        Entity player = event.getObject();
        if (!(player instanceof Player) || player.getCapability(PlayerAFKStateProvider.AFK_STATE).isPresent()) return;
        event.addCapability(new ResourceLocation(AFKCommandMod.MODID, "properties"), new PlayerAFKStateProvider());
    }

    @SubscribeEvent
    void playerCloned(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;
        event.getOriginal().getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(old -> {
            event.getOriginal().getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(newCap -> {
                newCap.copyFrom(old);
            });
        });
    }

    @SubscribeEvent
    void clientSetUp(ServerStartingEvent event) {
        AFKCommandMod.LOGGER.info("Setting up the server");
    }

    @SubscribeEvent
    void playerTicked(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.CLIENT) return;
        event.player.getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(cap -> {
            if (cap.isAFK()) cap.addTick();
        });
    }

}
