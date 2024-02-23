package com.aylanj123.afkcommand.networking.packets;


import com.aylanj123.afkcommand.networking.stateholder.ClientAFKStateHolder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.fml.DistExecutor;

import java.util.function.Supplier;

public class GoneAFKS2CPacket {

    public GoneAFKS2CPacket() {

    }

    public GoneAFKS2CPacket(FriendlyByteBuf buffer) {
        this();
    }

    public void encode(FriendlyByteBuf buffer) {

    }

    public static void handle(GoneAFKS2CPacket msg, CustomPayloadEvent.Context cx) {
        ClientAFKStateHolder.afk = true;
        cx.setPacketHandled(true);
    }

}
