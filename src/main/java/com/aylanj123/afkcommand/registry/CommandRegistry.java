package com.aylanj123.afkcommand.registry;

import com.aylanj123.afkcommand.afkstate.AFKStateHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;

public class CommandRegistry {


    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(CommandRegistry.getAFKCommand());
    }

    public static LiteralArgumentBuilder<CommandSourceStack> getAFKCommand() {
        return Commands.literal("afk")
            .executes(AFKStateHandler::AddAFKStateToPlayer)
            .then(getArgument())
            .requires(commSrc -> commSrc.hasPermission(2));
    }

    private static ArgumentBuilder<CommandSourceStack, ?> getArgument() {
        return Commands.argument("player", EntityArgument.entities())
                .executes(AFKStateHandler::AddAFKStateToAssignedPlayer);
    }

}
