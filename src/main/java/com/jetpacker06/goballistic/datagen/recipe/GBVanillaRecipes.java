package com.jetpacker06.goballistic.datagen.recipe;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.register.GBItems;
import com.simibubi.create.AllItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class GBVanillaRecipes extends RecipeProvider implements IConditionBuilder {
    private static Consumer<FinishedRecipe> c;

    public GBVanillaRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> cons) {
        GBVanillaRecipes.c = cons;
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
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(AllItems.CRUSHED_LEAD.get()), GBItems.LEAD_INGOT.get(), 1.0f, 200);
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(AllItems.CRUSHED_LEAD.get()), GBItems.LEAD_INGOT.get(), 1.0f, 100);

        // STONECUTTING
        cut("stamp_44_cal_casing", Items.HEAVY_WEIGHTED_PRESSURE_PLATE, GBItems.STAMP_44_CAL_CASING.get());
        cut("stamp_44_cal_bullet", Items.HEAVY_WEIGHTED_PRESSURE_PLATE, GBItems.STAMP_44_CAL_BULLET.get());
        cut("stamp_22_cal_casing", Items.HEAVY_WEIGHTED_PRESSURE_PLATE, GBItems.STAMP_22_CAL_CASING.get());
        cut("stamp_22_cal_bullet", Items.HEAVY_WEIGHTED_PRESSURE_PLATE, GBItems.STAMP_22_CAL_BULLET.get());
    }

    @SuppressWarnings("SameParameterValue")
    private void cut(String name, ItemLike input, Item output) {
        cut(name, input, output, 1);
    }

    @SuppressWarnings("SameParameterValue")
    private void cut(String name, ItemLike input, Item output, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Items.HEAVY_WEIGHTED_PRESSURE_PLATE), output, count)
                .unlockedBy(getHasName(input), has(output)).save(c, GoBallistic.MOD_ID + ":stonecutting/" + name);
    }
}
