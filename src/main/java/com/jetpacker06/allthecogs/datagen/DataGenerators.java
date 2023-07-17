package com.jetpacker06.allthecogs.datagen;

import com.jetpacker06.allthecogs.AllTheCogs;
import com.jetpacker06.allthecogs.datagen.recipeproviders.AllTheCogsVanillaRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = AllTheCogs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        ExistingFileHelper helper = event.getExistingFileHelper();
        DataGenerator gen = event.getGenerator();
        gen.addProvider(new ATCLang(gen, AllTheCogs.MOD_ID, "en_us"));
        gen.addProvider(new AllTheCogsVanillaRecipes(gen));
    }
}
