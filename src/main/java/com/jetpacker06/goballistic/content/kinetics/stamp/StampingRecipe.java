package com.jetpacker06.goballistic.content.kinetics.stamp;

import com.google.gson.JsonObject;
import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.jei.GBSequencedAssemblySubCategories;
import com.jetpacker06.goballistic.register.GBBlocks;
import com.jetpacker06.goballistic.register.GBRecipeTypes;
import com.simibubi.create.compat.jei.category.sequencedAssembly.SequencedAssemblySubCategory;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.sequenced.IAssemblyRecipe;
import com.simibubi.create.foundation.utility.Components;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class StampingRecipe extends ProcessingRecipe<RecipeWrapper> implements IAssemblyRecipe {

    // keepHeldItem not functional yet
    private boolean keepHeldItem;

    public StampingRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(GBRecipeTypes.STAMPING, params);
        keepHeldItem = params.keepHeldItem;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean matches(RecipeWrapper inv, Level worldIn) {
        return getProcessedItem()
                .test(inv.getItem(0))
                && getRequiredHeldItem()
                .test(inv.getItem(1));
    }

    @Override
    protected int getMaxInputCount() {
        return 2;
    }

    @Override
    protected int getMaxOutputCount() {
        return 4;
    }

    public Ingredient getRequiredHeldItem() {
        if (ingredients.isEmpty())
            throw new IllegalStateException("Stamping Recipe: " + id.toString() + " has no tool!");
        return ingredients.get(1);
    }

    public Ingredient getProcessedItem() {
        if (ingredients.size() < 2)
            throw new IllegalStateException("Stamping Recipe: " + id.toString() + " has no ingredient!");
        return ingredients.get(0);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Component getDescriptionForAssembly() {
        ItemStack[] matchingStacks = ingredients.get(1)
                .getItems();
        if (matchingStacks.length == 0)
            return Components.literal("Invalid");
        return new TranslatableComponent(GoBallistic.MOD_ID + ".recipe.assembling.stamping_item");
    }

    @Override
    public void addRequiredMachines(@NotNull Set<ItemLike> list) {
        list.add(GBBlocks.MECHANICAL_STAMP.get());
    }

    @Override
    public void addAssemblyIngredients(List<Ingredient> list) {
        list.add(ingredients.get(1));
    }

    @Override
    public Supplier<Supplier<SequencedAssemblySubCategory>> getJEISubCategory() {
        return () -> GBSequencedAssemblySubCategories.AssemblyStamping::new;
    }
    @Override
    public void readAdditional(@NotNull JsonObject json) {
        super.readAdditional(json);
        keepHeldItem = GsonHelper.getAsBoolean(json, "keepHeldItem", false);
    }

    @Override
    public void writeAdditional(@NotNull JsonObject json) {
        super.writeAdditional(json);
        if (keepHeldItem)
            json.addProperty("keepHeldItem", true);
    }

    @Override
    public void readAdditional(@NotNull FriendlyByteBuf buffer) {
        super.readAdditional(buffer);
        keepHeldItem = buffer.readBoolean();
    }

    @Override
    public void writeAdditional(@NotNull FriendlyByteBuf buffer) {
        super.writeAdditional(buffer);
        buffer.writeBoolean(keepHeldItem);
    }
}
