package com.aylanj123.afkcommand;

import com.aylanj123.afkcommand.eventhandler.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(AFKCommandMod.MODID)
public class AFKCommandMod
{
    public static final String MODID = "afk_command";

    // slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public AFKCommandMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        //modEventBus.register(new ServerEventHandler());
        forgeEventBus.register(new ServerEventHandler());
        modEventBus.register(new CommonEventHandler());
        modEventBus.register(new ClientEventHandler());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

}
