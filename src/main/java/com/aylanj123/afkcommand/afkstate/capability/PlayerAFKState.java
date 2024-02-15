package com.aylanj123.afkcommand.afkstate.capability;

import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.Config;
import com.aylanj123.afkcommand.LangKeys;
import com.aylanj123.afkcommand.networking.PacketHandler;
import com.aylanj123.afkcommand.networking.packets.GoneAFKS2CPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class PlayerAFKState {

    private boolean afk;
    private StateSource source;
    private int timeAFK;
    private long lastTimeAFK;

    public boolean isAFK() {
        return afk;
    }

    public StateSource getSource() {
        return source;
    }

    public int timeAFK() {
        return timeAFK;
    }

    public void addTick() {
        timeAFK++;
    }

    public long getLastTimeAFK() {
        return lastTimeAFK;
    }

    public void putAFK(StateSource source, ServerPlayer player) {
        this.source = source;
        afk = true;
        timeAFK = 0;
        PacketHandler.sendPlayer(new GoneAFKS2CPacket(), player);
        player.displayClientMessage(Component.translatable(LangKeys.COMMAND_ANSWER_ENTER.key()), true);
        updateSleep(player);
        if (Config.invinciblePlayers) player.setInvulnerable(true);
    }

    public void removeAFK(ServerPlayer player) {
        AFKCommandMod.LOGGER.info(String.format(
            "%s has returned from being AFK after ticks %o (%fs) and had gone AFK %s",
            player.getName().getString(), timeAFK, timeAFK / 20F,
            source == StateSource.SELF_APPLY ? "on their own" :
            source == StateSource.OPERATOR_APPLIED ? "because of an operator" : "on login"
        ));
        afk = false;
        timeAFK = -1;
        source = null;
        lastTimeAFK = player.serverLevel().getGameTime();
        player.displayClientMessage(Component.translatable(LangKeys.COMMAND_ANSWER_EXIT.key()), true);
        updateSleep(player);
        if (Config.invinciblePlayers) player.setInvulnerable(false);
    }

    public void copyFrom(PlayerAFKState oldState) {
        afk = oldState.afk;
        source = oldState.source;
        timeAFK = oldState.timeAFK;
    }

    private void updateSleep(ServerPlayer player) {
        player.serverLevel().updateSleepingPlayerList();
    }

}
