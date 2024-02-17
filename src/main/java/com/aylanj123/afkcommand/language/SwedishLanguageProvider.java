package com.aylanj123.afkcommand.language;

import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.LangKeys;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class SwedishLanguageProvider extends LanguageProvider {

    public SwedishLanguageProvider(PackOutput output, String locale) {
        super(output, AFKCommandMod.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(LangKeys.COMMAND_ANSWER_ENTER.key(), "Du är nu AFK");
        add(LangKeys.COMMAND_ANSWER_EXIT.key(), "Du är inte AFK längre");
        add(LangKeys.COMMAND_ANSWER_OTHER.key(), "Spelaren är nu AFK");
        add(LangKeys.STATE_ERROR_COOLDOWN.key(), "You are still on cooldown (%ss left)");
        add(LangKeys.STATE_ERROR_COMBAT.key(), "You are still in combat");
        add(LangKeys.STATE_ERROR_MONSTERS.key(), "There are monsters nearby");
        add(LangKeys.KICK_IDLE.key(), "You've idled for over %ss");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED_SELF.key(), "You are already AFK.");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED_OTHER.key(), "Spelaren är redan AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_SOURCE.key(), "Ingen spelare vald. Måste specificera vilken spelare som ska sättas AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_PLAYER.key(), "Den valda entiteten är inte en spelare.");
    }
}
