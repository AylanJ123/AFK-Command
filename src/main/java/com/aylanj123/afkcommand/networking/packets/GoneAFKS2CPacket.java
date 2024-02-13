package com.aylanj123.afkcommand.networking.packets;


import com.aylanj123.afkcommand.networking.stateholder.ClientAFKStateHolder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class GoneAFKS2CPacket {

    public GoneAFKS2CPacket() {

    }

    public GoneAFKS2CPacket(FriendlyByteBuf buffer) {
        this();
    }

    public void encode(FriendlyByteBuf buffer) {

    }

    public void handle(Supplier<NetworkEvent.ServerCustomPayloadEvent.Context> cxSupplier) {
        NetworkEvent.Context cx = cxSupplier.get();
        ClientAFKStateHolder.afk = true;
        cx.setPacketHandled(true);
    }

}
