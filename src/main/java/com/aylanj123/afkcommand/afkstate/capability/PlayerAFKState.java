package com.aylanj123.afkcommand.afkstate.capability;

import com.aylanj123.afkcommand.AFKCommandMod;
import net.minecraft.server.level.ServerPlayer;

public class PlayerAFKState {

    private boolean afk;
    private StateSource source;
    private int timeAFK;

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

    public void putAFK(StateSource source) {
        this.source = source;
        afk = true;
        timeAFK = 0;
    }

    public void removeAFK(ServerPlayer player) {
        AFKCommandMod.LOGGER.info(String.format(
            "%s has returned from being AFK after ticks %o (%fs) and was originally put %s",
            player.getName().getString(), timeAFK, timeAFK / 20F,
            source == StateSource.SELF_APPLY ? "by themselves" :
            source == StateSource.OPERATOR_APPLIED  ? "by an operator" : "on login"
        ));
        afk = false;
        timeAFK = -1;
        source = null;
    }

    public void copyFrom(PlayerAFKState oldState) {
        afk = oldState.afk;
        source = oldState.source;
        timeAFK = oldState.timeAFK;
    }

}
