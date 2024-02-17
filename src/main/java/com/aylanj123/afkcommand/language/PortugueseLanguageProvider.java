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
        add(LangKeys.STATE_ERROR_COOLDOWN.key(), "You are still on cooldown (%ss left)");
        add(LangKeys.STATE_ERROR_COMBAT.key(), "You are still in combat");
        add(LangKeys.STATE_ERROR_MONSTERS.key(), "There are monsters nearby");
        add(LangKeys.KICK_IDLE.key(), "You've idled for over %ss");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED_SELF.key(), "You are already AFK.");
        add(LangKeys.COMMAND_ERROR_STATE_APPLIED_OTHER.key(), "O jogador já estava AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_SOURCE.key(), "Nenhum jogador selecionado. Deve especificar qual jogador para ser posto como AFK.");
        add(LangKeys.COMMAND_ERROR_INVALID_PLAYER.key(), "A entidade selecionada não é um jogador.");
    }
}
