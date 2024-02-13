package com.aylanj123.afkcommand.language;

import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.LangKeys;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class EnglishLanguageProvider extends LanguageProvider {

    public EnglishLanguageProvider(PackOutput output, String locale) {
        super(output, AFKCommandMod.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(LangKeys.COMMAND_ANSWER_ENTER.key(), "You are now AFK");
        add(LangKeys.COMMAND_ANSWER_EXIT.key(), "You aren't AFK anymore");
        add(LangKeys.COMMAND_ANSWER_OTHER.key(), "The player is now AFK");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED.key(), "The player was already AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_SOURCE.key(), "No player selected. Must specify which player to put AFK");
        add(LangKeys.COMMAND_ERROR_INVALID_PLAYER.key(), "The selected entity is not a player.");
    }
}
