package com.aylanj123.afkcommand.language;

import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.LangKeys;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class PortugueseLanguageProvider extends LanguageProvider {

    public PortugueseLanguageProvider(PackOutput output, String locale) {
        super(output, AFKCommandMod.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(LangKeys.COMMAND_ANSWER_ENTER.key(), "Você está agora AFK");
        add(LangKeys.COMMAND_ANSWER_EXIT.key(), "Você já não está AFK");
        add(LangKeys.COMMAND_ANSWER_OTHER.key(), "O jogador está agora AFK");
        add(LangKeys.STATE_ERROR_COOLDOWN.key(), "Você ainda está no tempo de recarga (%s segundos restantes)");
        add(LangKeys.STATE_ERROR_COMBAT.key(), "Você ainda está em combate (%s segundos restantes)");
        add(LangKeys.STATE_ERROR_MONSTERS.key(), "Ameaças escondidas observam-te por perto...");
        add(LangKeys.KICK_IDLE.key(), "Você esteve inativo por %s segundos a mais");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED_SELF.key(), "Você já está AFK.");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED_OTHER.key(), "O jogador já estava AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_SOURCE.key(), "Nenhum jogador selecionado. Deve especificar qual jogador para ser posto como AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_PLAYER.key(), "A entidade selecionada não é um jogador.");
    }
}
