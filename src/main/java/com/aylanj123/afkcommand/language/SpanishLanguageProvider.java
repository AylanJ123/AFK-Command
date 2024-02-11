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
        add(LangKeys.COMMAND_ERROR_INVALID_SOURCE.key(), "Si no es un jugador el que ejecuta el comando, se debe especificar el mismo.");
        add(LangKeys.COMMAND_ERROR_INVALID_PLAYER.key(), "La entidad seleccionada no es un jugador.");
    }
}
