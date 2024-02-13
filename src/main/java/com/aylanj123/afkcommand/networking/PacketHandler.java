package com.aylanj123.afkcommand.networking;

import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.networking.packets.GoneAFKS2CPacket;
import com.aylanj123.afkcommand.networking.packets.MovedC2SPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Objects;
import java.util.function.Supplier;

public class PacketHandler {

    private static final SimpleChannel INSTANCE = ChannelBuilder.named(
            new ResourceLocation(AFKCommandMod.MODID, "main")
        ).serverAcceptedVersions((status) -> true)
        .clientAcceptedVersions((status) -> true)
        .networkProtocolVersion(() -> "1")
        .simpleChannel();

    public static void register() {
        INSTANCE.messageBuilder(
            MovedC2SPacket.class, NetworkDirection.PLAY_TO_SERVER.ordinal()
                ).encoder(MovedC2SPacket::encode)
                .decoder(MovedC2SPacket::new)
                .consumerMainThread(MovedC2SPacket::handle)
                .add();
        INSTANCE.messageBuilder(
            GoneAFKS2CPacket.class, NetworkDirection.PLAY_TO_CLIENT.ordinal()
                ).encoder(GoneAFKS2CPacket::encode)
                .decoder(GoneAFKS2CPacket::new)
                .consumerMainThread(GoneAFKS2CPacket::handle)
                .add();
    }

    public static void sendServer(Object msg) {
        INSTANCE.send(PacketDistributor.SERVER.noArg(), msg);
    }

    public static void sendPlayer(Object msg, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

}
