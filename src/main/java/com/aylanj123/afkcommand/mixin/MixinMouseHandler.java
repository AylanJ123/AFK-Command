package com.aylanj123.afkcommand.mixin;

import com.aylanj123.afkcommand.networking.PacketHandler;
import com.aylanj123.afkcommand.networking.packets.MovedC2SPacket;
import com.aylanj123.afkcommand.networking.stateholder.ClientAFKStateHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MixinMouseHandler {

    @Final
    @Shadow
    private Minecraft minecraft;

    @Shadow
    private boolean mouseGrabbed;

    @Inject(at = @At("HEAD"), method = "onPress(JIII)V", cancellable = false)
    private void onPress(long pWindowPointer, int pButton, int pAction, int pModifiers, CallbackInfo ci) {
        if (aFKCommand$isUnfocusedOrNotPlaying()) return;
        aFKCommand$exitAFK();
    }

    @Inject(at = @At("HEAD"), method = "onScroll(JDD)V", cancellable = false)
    private void onScroll(long pWindowPointer, double pXOffset, double pYOffset, CallbackInfo ci) {
        if (aFKCommand$isUnfocusedOrNotPlaying()) return;
        aFKCommand$exitAFK();
    }

    @Inject(at = @At("HEAD"), method = "onMove(JDD)V", cancellable = false)
    private void onMove(long pWindowPointer, double pXpos, double pYpos, CallbackInfo ci) {
        if (aFKCommand$isUnfocusedOrNotPlaying()) return;
        aFKCommand$exitAFK();
    }

    @Unique
    private boolean aFKCommand$isUnfocusedOrNotPlaying() {
        return !minecraft.isWindowActive() || !mouseGrabbed || minecraft.player == null;
    }

    @Unique
    private void aFKCommand$exitAFK() {
        if (!ClientAFKStateHolder.afk) return;
        ClientAFKStateHolder.afk = false;
        PacketHandler.sendServer(new MovedC2SPacket());
    }

}
