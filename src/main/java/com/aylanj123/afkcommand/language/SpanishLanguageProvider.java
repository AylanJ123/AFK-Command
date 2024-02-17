package com.aylanj123.afkcommand.language;

import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.LangKeys;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class SpanishLanguageProvider extends LanguageProvider {

    public SpanishLanguageProvider(PackOutput output, String locale) {
        super(output, AFKCommandMod.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(LangKeys.COMMAND_ANSWER_ENTER.key(), "Ahora estás AFK");
        add(LangKeys.COMMAND_ANSWER_EXIT.key(), "Ya no estás AFK");
        add(LangKeys.COMMAND_ANSWER_OTHER.key(), "El jugador ahora está AFK");
        add(LangKeys.STATE_ERROR_COOLDOWN.key(), "Sigues en enfriamiento (faltan %ss)");
        add(LangKeys.STATE_ERROR_COMBAT.key(), "Aún estás en combate (faltan %ss)");
        add(LangKeys.STATE_ERROR_MONSTERS.key(), "Hay monstruos cerca");
        add(LangKeys.KICK_IDLE.key(), "Has estado quieto por más de %ss");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED_SELF.key(), "Ya estabas AFK.");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED_OTHER.key(), "El jugador ya estaba AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_SOURCE.key(), "Ningún jugador seleccionado. Se debe especificar el jugador a mandar AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_PLAYER.key(), "La entidad seleccionada no es un jugador.");
    }
}
