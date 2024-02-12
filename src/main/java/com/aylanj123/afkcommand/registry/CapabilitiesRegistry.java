package com.aylanj123.afkcommand.registry;

import com.aylanj123.afkcommand.afkstate.capability.PlayerAFKState;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class CapabilitiesRegistry {

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(PlayerAFKState.class);
    }

}
