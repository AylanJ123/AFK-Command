package com.aylanj123.afkcommand.language;

import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.LangKeys;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class GermanLanguageProvider extends LanguageProvider {

    public GermanLanguageProvider(PackOutput output, String locale) {
        super(output, AFKCommandMod.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(LangKeys.COMMAND_ANSWER_ENTER.key(), "Du bist nun AFK");
        add(LangKeys.COMMAND_ANSWER_EXIT.key(), "Du bist nicht mehr AFK");
        add(LangKeys.COMMAND_ANSWER_OTHER.key(), "Der Spieler ist nun AFK");
        add(LangKeys.STATE_ERROR_COOLDOWN.key(), "Du bist noch in der Abklingzeit. (%s Sek. übrig)");
        add(LangKeys.STATE_ERROR_COMBAT.key(), "Du bist noch im Kampf. (%s Sek. übrig)");
        add(LangKeys.STATE_ERROR_MONSTERS.key(), "Unsichtbare Bedrohungen lauern in der Nähe...");
        add(LangKeys.KICK_IDLE.key(), "Du warst %s Sek. untätig.");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED_SELF.key(), "Du bist bereits AFK.");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED_OTHER.key(), "Der Spieler ist bereits AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_SOURCE.key(), "Kein Spieler angegeben. Es muss ein Spieler angegeben werden, der AFK gehen soll.");
        add(LangKeys.COMMAND_ERROR_INVALID_PLAYER.key(), "Die ausgewählte entität ist kein Spieler.");
    }
}
