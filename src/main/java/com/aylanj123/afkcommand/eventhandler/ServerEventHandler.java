package com.aylanj123.afkcommand.eventhandler;
import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.afkstate.AFKStateHandler;
import com.aylanj123.afkcommand.registry.CommandRegistry;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ServerEventHandler {

    @SubscribeEvent
    void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(CommandRegistry.getAFKCommand());
        AFKCommandMod.LOGGER.info("Setting up the commands");
    }

    @SubscribeEvent
    void clientSetUp(ServerStartingEvent event) {
        AFKCommandMod.LOGGER.info("Setting up the server");
    }

}
