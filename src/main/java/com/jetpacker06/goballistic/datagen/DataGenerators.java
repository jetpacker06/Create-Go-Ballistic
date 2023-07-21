package com.jetpacker06.goballistic.datagen;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.datagen.recipeproviders.GBVanillaRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = GoBallistic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
      //  ExistingFileHelper helper = event.getExistingFileHelper();
        DataGenerator gen = event.getGenerator();
        gen.addProvider(new GBVanillaRecipes(gen));
    }
}
