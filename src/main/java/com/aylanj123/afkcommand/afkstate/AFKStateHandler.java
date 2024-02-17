package com.aylanj123.afkcommand.afkstate;

import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.Config;
import com.aylanj123.afkcommand.LangKeys;
import com.aylanj123.afkcommand.afkstate.capability.PlayerAFKStateProvider;
import com.aylanj123.afkcommand.afkstate.capability.StateSource;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.EntityGetter;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class AFKStateHandler {

    private static final SimpleCommandExceptionType STATE_APPLIED_SELF = new SimpleCommandExceptionType(Component.translatable(LangKeys.COMMAND_ERROR_STATE_APPLIED_SELF.key()));
    private static final SimpleCommandExceptionType STATE_APPLIED_OTHER = new SimpleCommandExceptionType(Component.translatable(LangKeys.COMMAND_ERROR_STATE_APPLIED_OTHER.key()));
    private static final SimpleCommandExceptionType INVALID_SOURCE = new SimpleCommandExceptionType(Component.translatable(LangKeys.COMMAND_ERROR_INVALID_SOURCE.key()));
    private static final SimpleCommandExceptionType INVALID_PLAYER = new SimpleCommandExceptionType(Component.translatable(LangKeys.COMMAND_ERROR_INVALID_PLAYER.key()));

    public static int AddAFKStateToPlayer(CommandContext<CommandSourceStack> cx) throws CommandSyntaxException {
        if (cx.getSource().getPlayer() == null)
            throw INVALID_SOURCE.create();
        return addState(cx.getSource().getPlayer(), cx);
    }

    public static int AddAFKStateToAssignedPlayer(CommandContext<CommandSourceStack> cx) throws CommandSyntaxException {
        Entity entity = EntityArgument.getEntity(cx, "player");
        if (entity instanceof ServerPlayer)
            return addState((ServerPlayer) entity, cx);
        else
            throw INVALID_PLAYER.create();
    }

    private static int addState(ServerPlayer player, CommandContext<CommandSourceStack> cx) throws CommandSyntaxException {
        boolean self = cx.getSource().isPlayer() && cx.getSource().getPlayer() == player;
        AtomicBoolean success = new AtomicBoolean(false);
        if (self) {
            AtomicBoolean commandError = new AtomicBoolean(true);
            player.getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(cap -> {
                if (cap.isAFK()) return;
                if (Config.invinciblePlayers) {
                    if (Config.combatTime + cap.getLastTimeCombat() > player.serverLevel().getGameTime()) {
                        long timeLeft = (Config.combatTime + cap.getLastTimeCombat() - player.serverLevel().getGameTime()) / 20;
                        player.displayClientMessage(Component.translatable(LangKeys.STATE_ERROR_COMBAT.key(), String.valueOf(timeLeft)), true);
                        commandError.set(false);
                        return;
                    } else if (getMonstersNearby(player)) {
                        player.displayClientMessage(Component.translatable(LangKeys.STATE_ERROR_MONSTERS.key()), true);
                        commandError.set(false);
                        return;
                    }
                }
                if (
                    Config.afkCooldown != -1 &&
                    cap.getLastTimeAFK() != 0 &&
                    Config.afkCooldown + cap.getLastTimeAFK() >
                    player.serverLevel().getGameTime()
                ) {
                    long timeLeft = (Config.afkCooldown + cap.getLastTimeAFK() - player.serverLevel().getGameTime()) / 20;
                    player.displayClientMessage(Component.translatable(LangKeys.STATE_ERROR_COOLDOWN.key(), String.valueOf(timeLeft)), true);
                    commandError.set(false);
                    return;
                }
                if (Config.chatConfirmation) cx.getSource().sendSuccess(() -> Component.translatable(LangKeys.COMMAND_ANSWER_ENTER.key()), false);
                AFKCommandMod.LOGGER.info(player.getName().getString() + " has gone AFK");
                cap.putAFK(StateSource.SELF_APPLY, player);
                success.set(true);
            });
            if (success.get()) return 1;
            else if (commandError.get()) throw STATE_APPLIED_SELF.create();
        } else {
            player.getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(cap -> {
                if (cap.isAFK()) return;
                success.set(true);
                cx.getSource().sendSuccess(() -> Component.translatable(LangKeys.COMMAND_ANSWER_OTHER.key()), false);
                AFKCommandMod.LOGGER.info(player.getName().getString() + " has been put AFK");
                cap.putAFK(StateSource.OPERATOR_APPLIED, player);
            });
            if (success.get()) return 1;
            else throw STATE_APPLIED_OTHER.create();
        }
        return 0;
    }

    private static boolean getMonstersNearby(ServerPlayer player) {
        List<Monster> monsters = player.serverLevel().getNearbyEntities(
            Monster.class,
            TargetingConditions.forCombat(),
            player,
            new AABB(
                player.getX() + 5,
                player.getY() + 3,
                player.getZ() + 5,
                player.getX() - 5,
                player.getY() - 3,
                player.getZ() - 5
            )
        );
        return !monsters.isEmpty();
    }

}
