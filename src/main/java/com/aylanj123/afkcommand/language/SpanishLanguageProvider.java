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
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED.key(), "El jugador ya estaba AFK");
        add(LangKeys.COMMAND_ERROR_INVALID_SOURCE.key(), "Ningún jugador seleccionado. Se debe especificar el jugador a mandar AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_PLAYER.key(), "La entidad seleccionada no es un jugador.");
    }
}
