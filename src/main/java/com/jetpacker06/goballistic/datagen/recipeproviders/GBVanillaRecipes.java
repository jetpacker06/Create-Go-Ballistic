package com.jetpacker06.goballistic.datagen.recipeproviders;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class GBVanillaRecipes extends RecipeProvider implements IConditionBuilder {

    public GBVanillaRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> c) {
        // CRAFTING
      //  ShapedRecipeBuilder.shaped(AllItems.ANDESITE_ALLOY.get(), 1)
      //          .define('N', AllECItems.ANDESITE_ALLOY_NUGGET.get())
      //          .pattern("NNN")
      //          .pattern("NNN")
      //          .pattern("NNN")
      //          .unlockedBy("has_andesite_alloy", inventoryTrigger(ItemPredicate.Builder.item()
      //                  .of(AllItems.ANDESITE_ALLOY.get()).build()))
      //          .save(c);
        // SMELTING
       // SimpleCookingRecipeBuilder.smelting(Ingredient.of(AllECItems.CRUSHED_RAW_COBALT.get()), TinkerMaterials.cobalt, 1.0f, 200);
    }
}
