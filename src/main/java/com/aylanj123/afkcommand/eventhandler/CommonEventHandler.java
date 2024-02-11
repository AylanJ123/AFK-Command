package com.aylanj123.afkcommand.eventhandler;
import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.language.EnglishLanguageProvider;
import com.aylanj123.afkcommand.language.SpanishLanguageProvider;
import net.minecraft.data.DataProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.List;

public class CommonEventHandler {

    private static final List<String> englishLocales = new ArrayList<>(List.of(
        "en_us", "en_nz", "en_gb", "en_ca", "en_au"
    ));

    private static final List<String> spanishLocales = new ArrayList<>(List.of(
        "es_ve", "es_uy", "es_mx", "es_es", "es_ec", "es_cl", "es_ar"
    ));

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        for (String locale : englishLocales)
            event.getGenerator().addProvider(
                    event.includeClient(),
                    (DataProvider.Factory<EnglishLanguageProvider>)
                            output -> new EnglishLanguageProvider(output, locale)
            );
        for (String locale : spanishLocales)
            event.getGenerator().addProvider(
                    event.includeClient(),
                    (DataProvider.Factory<SpanishLanguageProvider>)
                            output -> new SpanishLanguageProvider(output, locale)
            );
        AFKCommandMod.LOGGER.info("Generating new data");
    }

    @SubscribeEvent
    void clientSetUp(FMLCommonSetupEvent event) {
        AFKCommandMod.LOGGER.info("Common Set Up");
    }

}
