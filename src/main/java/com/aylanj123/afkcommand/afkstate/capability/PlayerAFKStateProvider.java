package com.aylanj123.afkcommand.afkstate.capability;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerAFKStateProvider implements ICapabilityProvider {

    public static Capability<PlayerAFKState> AFK_STATE = CapabilityManager.get(new CapabilityToken<PlayerAFKState>() {
    });

    private PlayerAFKState afkState = null;
    private final LazyOptional<PlayerAFKState> lazy = LazyOptional.of(this::createAFKState);

    public PlayerAFKState createAFKState() {
        if (afkState == null) afkState = new PlayerAFKState();
        return afkState;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == AFK_STATE ? lazy.cast() : LazyOptional.empty();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return ICapabilityProvider.super.getCapability(cap);
    }
}
