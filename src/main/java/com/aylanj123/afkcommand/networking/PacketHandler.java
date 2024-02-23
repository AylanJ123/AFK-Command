package com.aylanj123.afkcommand.networking;

import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.networking.packets.GoneAFKS2CPacket;
import com.aylanj123.afkcommand.networking.packets.IdleConfigS2CPacket;
import com.aylanj123.afkcommand.networking.packets.IdledC2SPacket;
import com.aylanj123.afkcommand.networking.packets.MovedC2SPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.*;

import java.util.Objects;
import java.util.function.Supplier;

public class PacketHandler {

    private static SimpleChannel INSTANCE;

    public static void register() {
        INSTANCE = ChannelBuilder.named(new ResourceLocation(AFKCommandMod.MODID, "main"))
            .acceptedVersions(Channel.VersionTest.exact(1))
            .networkProtocolVersion(1)
            .simpleChannel()

            .messageBuilder(
                    MovedC2SPacket.class, 1, NetworkDirection.PLAY_TO_SERVER
            ).encoder(MovedC2SPacket::encode)
            .decoder(MovedC2SPacket::new)
            .consumerMainThread(MovedC2SPacket::handle)
            .add()

            .messageBuilder(
                    IdledC2SPacket.class, 2, NetworkDirection.PLAY_TO_SERVER
            ).encoder(IdledC2SPacket::encode)
            .decoder(IdledC2SPacket::new)
            .consumerMainThread(IdledC2SPacket::handle)
            .add()

            .messageBuilder(
                    GoneAFKS2CPacket.class, 3, NetworkDirection.PLAY_TO_CLIENT
            ).encoder(GoneAFKS2CPacket::encode)
            .decoder(GoneAFKS2CPacket::new)
            .consumerMainThread(GoneAFKS2CPacket::handle)
            .add()

            .messageBuilder(
                    IdleConfigS2CPacket.class, 4, NetworkDirection.PLAY_TO_CLIENT
            ).encoder(IdleConfigS2CPacket::encode)
            .decoder(IdleConfigS2CPacket::new)
            .consumerMainThread(IdleConfigS2CPacket::handle)
            .add();
    }

    public static void sendServer(Object msg) {
        INSTANCE.send(msg, PacketDistributor.SERVER.noArg());
    }

    public static void sendPlayer(Object msg, ServerPlayer player) {
        INSTANCE.send(msg, PacketDistributor.PLAYER.with(player));
    }

}
