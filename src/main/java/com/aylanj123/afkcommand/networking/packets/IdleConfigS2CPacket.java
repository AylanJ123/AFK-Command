package com.aylanj123.afkcommand.networking.packets;


import com.aylanj123.afkcommand.afkstate.capability.PlayerAFKStateProvider;
import com.aylanj123.afkcommand.afkstate.capability.StateSource;
import com.aylanj123.afkcommand.networking.stateholder.ClientAFKStateHolder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.fml.DistExecutor;

import java.util.function.Supplier;

public class IdleConfigS2CPacket {

    int time;

    public IdleConfigS2CPacket(int time) {
        this.time = time;
    }

    public IdleConfigS2CPacket(FriendlyByteBuf buffer) {
        this(buffer.readInt());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.time);
    }

    public static void handle(IdleConfigS2CPacket msg, CustomPayloadEvent.Context cx) {
        ClientAFKStateHolder.timeIdle = msg.time;
        cx.setPacketHandled(true);
    }

}
