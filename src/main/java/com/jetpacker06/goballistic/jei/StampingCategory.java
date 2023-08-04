package com.jetpacker06.goballistic.jei;

import com.jetpacker06.goballistic.content.kinetics.stamp.AnimatedStamp;
import com.jetpacker06.goballistic.content.kinetics.stamp.StampingRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StampingCategory extends CreateRecipeCategory<StampingRecipe> {

    private final AnimatedStamp stamp = new AnimatedStamp();

    public StampingCategory(Info<StampingRecipe> info) {
        super(info);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, StampingRecipe recipe, @NotNull IFocusGroup focuses) {
        builder
                .addSlot(RecipeIngredientRole.INPUT, 27, 51)
                .setBackground(getRenderedSlot(), -1, -1)
                .addIngredients(recipe.getProcessedItem());
        builder
                .addSlot(RecipeIngredientRole.INPUT, 51, 5)
                .setBackground(getRenderedSlot(), -1, -1)
                .addIngredients(recipe.getRequiredHeldItem());

        List<ProcessingOutput> results = recipe.getRollableResults();
        boolean single = results.size() == 1;
        for (int i = 0; i < results.size(); i++) {
            ProcessingOutput output = results.get(i);
            int xOffset = i % 2 == 0 ? 0 : 19;
            int yOffset = (i / 2) * -19;
            builder.addSlot(RecipeIngredientRole.OUTPUT, single ? 132 : 132 + xOffset, 51 + yOffset)
                    .setBackground(getRenderedSlot(output), -1, -1)
                    .addItemStack(output.getStack())
                    .addTooltipCallback(addStochasticTooltip(output));
        }
    }

    @Override
    public void draw(@NotNull StampingRecipe recipe, @NotNull IRecipeSlotsView iRecipeSlotsView, @NotNull PoseStack matrixStack, double mouseX, double mouseY) {
        AllGuiTextures.JEI_SHADOW.render(matrixStack, 62, 57);
        AllGuiTextures.JEI_DOWN_ARROW.render(matrixStack, 126, 29 + (recipe.getRollableResults().size() > 2 ? -19 : 0));
        stamp.draw(matrixStack, getBackground().getWidth() / 2 - 13, 22);
    }
}
