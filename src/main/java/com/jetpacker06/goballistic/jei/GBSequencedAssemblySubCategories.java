package com.jetpacker06.goballistic.jei;

import com.jetpacker06.goballistic.content.kinetics.stamp.AnimatedStamp;
import com.jetpacker06.goballistic.content.kinetics.stamp.StampingRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.compat.jei.category.sequencedAssembly.SequencedAssemblySubCategory;
import com.simibubi.create.content.processing.sequenced.SequencedRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;

public class GBSequencedAssemblySubCategories {
    public static class AssemblyStamping extends SequencedAssemblySubCategory {

        AnimatedStamp stamp;

        public AssemblyStamping() {
            super(25);
            stamp = new AnimatedStamp();
        }

        @Override
        public void setRecipe(IRecipeLayoutBuilder builder, SequencedRecipe<?> recipe, IFocusGroup focuses, int x) {
            builder
                    .addSlot(RecipeIngredientRole.INPUT, x + 4, 15)
                    .setBackground(CreateRecipeCategory.getRenderedSlot(), -1, -1)
                    .addIngredients((((StampingRecipe) recipe.getRecipe()).getRequiredHeldItem()));
        }

        @Override
        public void draw(SequencedRecipe<?> recipe, PoseStack ms, double mouseX, double mouseY, int index) {
            stamp.offset = index;
            ms.pushPose();
            ms.translate(-7, 50, 0);
            ms.scale(.75f, .75f, .75f);
            stamp.draw(ms, getWidth() / 2, 0);
            ms.popPose();
        }

    }
}
