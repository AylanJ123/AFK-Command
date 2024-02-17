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
        add(LangKeys.STATE_ERROR_COOLDOWN.key(), "Du är fortfarande på cooldown (%sek kvar)");
        add(LangKeys.STATE_ERROR_COMBAT.key(), "Du är fortfarane i strid (%sek kvar)");
        add(LangKeys.STATE_ERROR_MONSTERS.key(), "Osedda hot lurar i närheten...");
        add(LangKeys.KICK_IDLE.key(), "Du var borta över %sek");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED_SELF.key(), "Du är redan AFK.");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED_OTHER.key(), "Spelaren är redan AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_SOURCE.key(), "Ingen spelare vald. Måste specificera vilken spelare som ska sättas AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_PLAYER.key(), "Den valda entiteten är inte en spelare.");
    }
}
