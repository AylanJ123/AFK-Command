package com.aylanj123.afkcommand;

public enum LangKeys {
    COMMAND_ANSWER_ENTER("commands.afk.enter"),
    COMMAND_ANSWER_EXIT("commands.afk.exit"),
    COMMAND_ANSWER_OTHER("commands.afk.other"),
    STATE_ERROR_COOLDOWN("state.error.cooldown"),
    STATE_ERROR_COMBAT("state.error.combat"),
    STATE_ERROR_MONSTERS("state.error.monsters"),
    COMMAND_ERROR_STATE_APPLIED_SELF("commands.error.state.applied.self"),
    COMMAND_ERROR_STATE_APPLIED_OTHER("commands.error.state.applied.other"),
    COMMAND_ERROR_INVALID_SOURCE("commands.error.invalid.source"),
    COMMAND_ERROR_INVALID_PLAYER("commands.error.invalid.player");

    private final String key;

    LangKeys(String key) {
        this.key = AFKCommandMod.MODID + "." + key;
    }

    public String key() {
        return key;
    }

    @Override
    public String toString() {
        return key;
    }
}