package com.aylanj123.afkcommand.mixin;

import com.aylanj123.afkcommand.networking.PacketHandler;
import com.aylanj123.afkcommand.networking.packets.MovedC2SPacket;
import com.aylanj123.afkcommand.networking.stateholder.ClientAFKStateHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.KeyboardInput;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public class MixinKeyboardInput {

    @Final
    @Shadow
    private Options options;

    @Inject(at = @At("HEAD"), method = "tick(ZF)V", cancellable = false)
    private void tick(boolean pIsSneaking, float pSneakingSpeedMultiplier, CallbackInfo ci) {
        if (options == null) return;
        boolean pressed = options.keyUp.isDown() ||
                options.keyDown.isDown() ||
                options.keyLeft.isDown() ||
                options.keyRight.isDown() ||
                options.keyJump.isDown() ||
                options.keyShift.isDown();
        if (pressed) {
            ClientAFKStateHolder.currentIdleTime = 0;
            if (ClientAFKStateHolder.afk)
                aFKCommand$exitAFK();
        }
    }

    @Unique
    private void aFKCommand$exitAFK() {
        ClientAFKStateHolder.afk = false;
        PacketHandler.sendServer(new MovedC2SPacket());
    }

}
