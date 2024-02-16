package com.aylanj123.afkcommand;

import com.aylanj123.afkcommand.eventhandler.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import org.slf4j.Logger;

@Mod(AFKCommandMod.MODID)
public class AFKCommandMod
{
    public static final String MODID = "afk_command";

    // slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public AFKCommandMod()
    {
        MinecraftForge.EVENT_BUS.register(ServerEventHandler.ServerForgeEvents.class);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC, "afk_command-server.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC, "afk_command-client.toml");
    }

}
