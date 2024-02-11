package com.aylanj123.afkcommand.eventhandler;
import com.aylanj123.afkcommand.AFKCommandMod;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEventHandler {

    @SubscribeEvent
    void clientSetUp(FMLClientSetupEvent event) {
        AFKCommandMod.LOGGER.info("Setting up the client");
    }

}
