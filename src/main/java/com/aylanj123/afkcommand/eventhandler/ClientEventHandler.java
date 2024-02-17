package com.aylanj123.afkcommand.eventhandler;
import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.Config;
import com.aylanj123.afkcommand.networking.PacketHandler;
import com.aylanj123.afkcommand.networking.packets.IdledC2SPacket;
import com.aylanj123.afkcommand.networking.stateholder.ClientAFKStateHolder;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEventHandler {

    @Mod.EventBusSubscriber(modid = AFKCommandMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        static void clientSetUp(FMLClientSetupEvent event) {
            AFKCommandMod.LOGGER.info("Setting up the client");
            Config.clientSidedLoad();
        }

    }

    @Mod.EventBusSubscriber(modid = AFKCommandMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        static void clientTick(TickEvent.ClientTickEvent event) {
            if (event.side != LogicalSide.CLIENT || Minecraft.getInstance().player == null) return;
            if (++ClientAFKStateHolder.currentIdleTime > ClientAFKStateHolder.timeIdle) {
                ClientAFKStateHolder.currentIdleTime = 0;
                PacketHandler.sendServer(new IdledC2SPacket());
            }
        }

    }

}
