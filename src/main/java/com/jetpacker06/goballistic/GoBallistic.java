package com.jetpacker06.goballistic;

import com.jetpacker06.goballistic.content.GBTab;
import com.jetpacker06.goballistic.datagen.DataGenerators;
import com.jetpacker06.goballistic.datagen.GBLang;
import com.jetpacker06.goballistic.datagen.GBTagGen;
import com.jetpacker06.goballistic.register.*;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(GoBallistic.MOD_ID)
public class GoBallistic {
    public static final String MOD_ID = "goballistic";
    public static final String NAME = "Create: Go Ballistic";
    public static CreateRegistrate REGISTRATE;

    public GoBallistic() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRATE = CreateRegistrate.create(MOD_ID).registerEventListeners(eventBus);
        REGISTRATE.creativeModeTab(() -> GBTab.GBT);

        GBItems.registerItems(REGISTRATE);
        GBBlocks.registerBlocks(REGISTRATE);
        GBBlockEntities.registerBlockEntities(REGISTRATE);
        GBFluids.registerFluids(REGISTRATE);

        GBRecipeTypes.Registers.register(eventBus);

        GBLang.register(REGISTRATE);
        eventBus.addListener(EventPriority.LOWEST, this::gatherData);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void gatherData(GatherDataEvent event) {
        GBTagGen.datagen(REGISTRATE);
        if (event.includeServer()) {
            DataGenerators.gatherData(event);
        }
    }
}
