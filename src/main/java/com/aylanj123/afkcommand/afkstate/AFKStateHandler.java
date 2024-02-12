package com.aylanj123.afkcommand.afkstate;

import com.aylanj123.afkcommand.AFKCommandMod;
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

import java.util.concurrent.atomic.AtomicBoolean;

public class AFKStateHandler {

    private static final SimpleCommandExceptionType STATE_APPLIED = new SimpleCommandExceptionType(Component.translatable(LangKeys.COMMAND_ERROR_STATE_APPLIED.key()));
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
        StateSource source = null;
        if (self) {
            cx.getSource().sendSuccess(() -> Component.translatable(LangKeys.COMMAND_ANSWER_ENTER.key()), false);
            AFKCommandMod.LOGGER.info(player.getName().getString() + " has gone AFK");
            source = StateSource.SELF_APPLY;
        }
        if (!self || !cx.getSource().isPlayer()) {
            cx.getSource().sendSuccess(() -> Component.translatable(LangKeys.COMMAND_ANSWER_OTHER.key()), false);
            AFKCommandMod.LOGGER.info(player.getName().getString() + " has been put AFK");
            source = StateSource.OPERATOR_APPLIED;
        }
        StateSource finalSource = source;
        AtomicBoolean success = new AtomicBoolean(false);
        player.getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(cap -> {
            if (cap.isAFK()) return;
            success.set(true);
            cap.putAFK(finalSource);
        });
        if (!success.get()) throw STATE_APPLIED.create();
        return 1;
    }

}
