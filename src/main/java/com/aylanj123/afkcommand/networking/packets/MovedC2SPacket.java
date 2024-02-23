package com.aylanj123.afkcommand.networking.packets;

import com.aylanj123.afkcommand.afkstate.capability.PlayerAFKState;
import com.aylanj123.afkcommand.afkstate.capability.PlayerAFKStateProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.fml.DistExecutor;

import java.util.function.Supplier;

public class MovedC2SPacket {

    public MovedC2SPacket() {

    }

    public MovedC2SPacket(FriendlyByteBuf buffer) {
        this();
    }

    public void encode(FriendlyByteBuf buffer) {

    }

    public static void handle(MovedC2SPacket msg, CustomPayloadEvent.Context cx) {
        ServerPlayer player = cx.getSender();
        if (player == null) {
            cx.setPacketHandled(false);
            return;
        }
        player.getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(cap -> cap.removeAFK(player));
        cx.setPacketHandled(true);
    }

}
