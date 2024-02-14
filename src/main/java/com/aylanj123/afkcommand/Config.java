package com.aylanj123.afkcommand;

import com.aylanj123.afkcommand.AFKCommandMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = AFKCommandMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<Boolean> INVINCIBLE_PLAYERS = SERVER_BUILDER
            .comment("Players should be invincible and untargetable while AFK. If true, players can't go AFK during combat or if monsters are nearby.")
            .define("invinciblePlayers", false);
    public static final ForgeConfigSpec.ConfigValue<Integer> AFK_KICK_TIME = SERVER_BUILDER
            .comment("Amount of ticks that should pass before kicking the player if AFK. Set it to -1 to turn off.")
            .define("kickAfterTooLong", -1);
    public static final ForgeConfigSpec.ConfigValue<Integer> AUTO_AFK_TIME = SERVER_BUILDER
            .comment("Amount of ticks that should pass before putting the player on AFK. Set it to -1 to turn off. (Default is 3600 and that's 3 minutes).")
            .define("autoApplyAFKAfterTooLong", 3600);
    public static final ForgeConfigSpec.ConfigValue<Boolean> AFK_ON_LOGIN = SERVER_BUILDER
            .comment("Players should spawn AFK on login. If combined with invinciblePlayers allows for a safe spawn.")
            .define("afkOnLogin", true);
    public static final ForgeConfigSpec.ConfigValue<Integer> AFK_COOLDOWN = SERVER_BUILDER
            .comment("Amount of ticks that should pass before the player can go AFK again on their own. Set it to -1 to turn off. (Default is 12000 and that's 10 minutes).")
            .define("afkCoolDown", 12000);

    public static final ForgeConfigSpec.ConfigValue<Boolean> CHAT_CONFIRMATION = CLIENT_BUILDER
            .comment("Set to true if you want to receive a chat confirmation of your AFK state when self applied.")
            .define("chatConfirmation", true);

    static final ForgeConfigSpec SERVER_SPEC = SERVER_BUILDER.build();
    static final ForgeConfigSpec CLIENT_SPEC = CLIENT_BUILDER.build();

    public static boolean invinciblePlayers;
    public static int timeToKick;
    public static int timeToSendAFK;
    public static boolean afkOnLogin;
    public static int afkCooldown;
    public static boolean chatConfirmation;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        invinciblePlayers = INVINCIBLE_PLAYERS.get();
        timeToKick = AFK_KICK_TIME.get();
        timeToSendAFK = AUTO_AFK_TIME.get();
        afkOnLogin = AFK_ON_LOGIN.get();
        afkCooldown = AFK_COOLDOWN.get();
        chatConfirmation = CHAT_CONFIRMATION.get();
    }

}
