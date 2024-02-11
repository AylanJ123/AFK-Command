package com.aylanj123.afkcommand.afkstate;

import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.LangKeys;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AFKStateHandler {

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

    private static int addState(ServerPlayer player, CommandContext<CommandSourceStack> cx) {
        AFKCommandMod.LOGGER.info("The given player is: " + player.getName().getString() + " with UUID " + player.getUUID());
        cx.getSource().sendSuccess(() -> Component.translatable(LangKeys.COMMAND_ANSWER_ENTER.key()), false);
        return 1;
    }

}
