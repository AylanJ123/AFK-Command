package com.aylanj123.afkcommand.networking.stateholder;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientAFKStateHolder {

    public static boolean afk = false;
    public static int timeIdle = 400;
    public static int currentIdleTime = 0;

}
