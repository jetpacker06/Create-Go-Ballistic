package com.jetpacker06.goballistic.datagen.recipe;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.register.GBBlocks;
import com.jetpacker06.goballistic.register.GBItems;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
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
        final String CRAFTING = "crafting/";
        final String SMELTING = "smelting/";
        final String BLASTING = "blasting/";

        // CRAFTING
        ShapedRecipeBuilder.shaped(GBBlocks.MECHANICAL_STAMP.get(), 1)
                .define('S', AllBlocks.SHAFT.get())
                .define('C', AllBlocks.BRASS_CASING.get())
                .define('I', AllTags.forgeItemTag("storage_blocks/brass"))
                .pattern("S")
                .pattern("C")
                .pattern("I")
                .unlockedBy("has_brass_casing", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(AllBlocks.BRASS_CASING.get()).build()))
                .save(c, CRAFTING + "mechanical_stamp");

        ShapedRecipeBuilder.shaped(GBBlocks.LEAD_BLOCK.get(), 1)
                .define('I', GBItems.LEAD_INGOT.get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .unlockedBy("has_lead_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(GBItems.LEAD_INGOT.get()).build()))
                .save(c, CRAFTING + "lead_block");

        ShapelessRecipeBuilder.shapeless(GBItems.LEAD_INGOT.get(), 9)
                .requires(GBBlocks.LEAD_BLOCK.get())
                .unlockedBy("has_lead_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(GBItems.LEAD_INGOT.get()).build()))
                .save(c, CRAFTING + "lead_ingot_from_block");

        // SMELTING
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(AllItems.CRUSHED_LEAD.get()), GBItems.LEAD_INGOT.get(), 1.0f, 200)
                .unlockedBy("has_crushed_lead", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(AllItems.CRUSHED_LEAD.get()).build()))
                .save(c, SMELTING + "lead_ingot");

        // BLASTING
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(AllItems.CRUSHED_LEAD.get()), GBItems.LEAD_INGOT.get(), 1.0f, 100)
                .unlockedBy("has_crushed_lead", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(AllItems.CRUSHED_LEAD.get()).build()))
                .save(c, BLASTING + "lead_ingot");

        // STONECUTTING
        cut("stamp_44_cal_casing", Items.HEAVY_WEIGHTED_PRESSURE_PLATE, GBItems.STAMP_44_CAL_CASING.get());
        cut("stamp_44_cal_bullet", Items.HEAVY_WEIGHTED_PRESSURE_PLATE, GBItems.STAMP_44_CAL_BULLET.get());
        cut("stamp_22_cal_casing", Items.HEAVY_WEIGHTED_PRESSURE_PLATE, GBItems.STAMP_22_CAL_CASING.get());
        cut("stamp_22_cal_bullet", Items.HEAVY_WEIGHTED_PRESSURE_PLATE, GBItems.STAMP_22_CAL_BULLET.get());

       // cut("shotgun_pellets", GBItems.LEAD_INGOT.get(), GBItems.SHELL_PELLETS.get(), 2);
    }

    @SuppressWarnings("SameParameterValue")
    private void cut(String name, ItemLike input, Item output) {
        cut(name, input, output, 1);
    }

    @SuppressWarnings("SameParameterValue")
    private void cut(String name, ItemLike input, Item output, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), output, count)
                .unlockedBy(getHasName(input), has(output)).save(c, GoBallistic.MOD_ID + ":stonecutting/" + name);
    }
}
