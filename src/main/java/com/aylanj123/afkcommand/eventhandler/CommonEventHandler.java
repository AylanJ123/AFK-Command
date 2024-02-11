package com.aylanj123.afkcommand.eventhandler;
import com.aylanj123.afkcommand.AFKCommandMod;
import com.aylanj123.afkcommand.language.*;
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

    private static final List<String> germanLocales = new ArrayList<>(List.of(
        "de_de", "de_at", "de_ch", "nds_de"
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
        for (String locale : germanLocales)
            event.getGenerator().addProvider(
                    event.includeClient(),
                    (DataProvider.Factory<GermanLanguageProvider>)
                            output -> new GermanLanguageProvider(output, locale)
            );
        event.getGenerator().addProvider(
                event.includeClient(),
                (DataProvider.Factory<SwedishLanguageProvider>)
                        output -> new SwedishLanguageProvider(output, "sv_se")
        );
        AFKCommandMod.LOGGER.info("Generating new data");
    }

    @SubscribeEvent
    void clientSetUp(FMLCommonSetupEvent event) {
        AFKCommandMod.LOGGER.info("Common Set Up");
    }

}
