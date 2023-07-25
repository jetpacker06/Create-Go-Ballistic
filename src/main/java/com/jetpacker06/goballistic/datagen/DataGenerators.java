package com.jetpacker06.goballistic.datagen;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.datagen.recipeproviders.GBCreateRecipeGen;
import com.jetpacker06.goballistic.datagen.recipeproviders.GBVanillaRecipes;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DataGenerators {
    protected static final List<CreateRecipeProvider> GENERATORS = new ArrayList<>();

    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        System.out.println("hhhh");
        if (event.includeServer()) {
            System.out.println("rrrr");
            gen.addProvider(new GBVanillaRecipes(gen));

            GENERATORS.add(new GBCreateRecipeGen.Splashing(gen));
            GENERATORS.add(new GBCreateRecipeGen.Mixing(gen));
            GENERATORS.add(new GBCreateRecipeGen.Cutting(gen));
            GENERATORS.add(new GBCreateRecipeGen.Filling(gen));
            GENERATORS.add(new GBCreateRecipeGen.Crushing(gen));
            GENERATORS.add(new GBCreateRecipeGen.Pressing(gen));
            GENERATORS.add(new GBCreateRecipeGen.Deploying(gen));
            GENERATORS.add(new GBCreateRecipeGen.Milling(gen));
            GENERATORS.add(new GBCreateRecipeGen.Stamping(gen));
            GENERATORS.add(new GBCreateRecipeGen.MechanicalCrafting(gen));

            gen.addProvider(new DataProvider() {

                @Override
                public @NotNull String getName() {
                    return GoBallistic.NAME + "'s Processing Recipes";
                }

                @Override
                public void run(@NotNull HashCache dc) {
                    GENERATORS.forEach(g -> {
                        try {
                            g.run(dc);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            });
        }
    }
}
