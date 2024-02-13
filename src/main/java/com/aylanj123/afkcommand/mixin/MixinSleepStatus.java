package com.aylanj123.afkcommand.mixin;

import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.afkstate.capability.PlayerAFKStateProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.SleepStatus;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(SleepStatus.class)
public class MixinSleepStatus {

    @Shadow
    private int activePlayers;
    @Shadow
    private int sleepingPlayers;

    /**
     * @author AylanJ123
     * @reason Had to overwrite update as overwriting
     * the amount of needed players to sleep was causing
     * logical bugs. No other mod should be changing this either
     * and if they do, deserves to crash for incompatibility.
     */
    @Overwrite
    public boolean update(List<ServerPlayer> pPlayers) {
        int i = this.activePlayers;
        int j = this.sleepingPlayers;
        this.activePlayers = 0;
        this.sleepingPlayers = 0;

        for(ServerPlayer serverplayer : pPlayers) {
            serverplayer.getCapability(PlayerAFKStateProvider.AFK_STATE).ifPresent(cap -> {
                if (!serverplayer.isSpectator() && !cap.isAFK()) {
                    ++this.activePlayers;
                    if (serverplayer.isSleeping()) {
                        ++this.sleepingPlayers;
                    }
                }
            });
        }

        return (j > 0 || this.sleepingPlayers > 0) && (i != this.activePlayers || j != this.sleepingPlayers);
    }

}
