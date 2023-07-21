package com.jetpacker06.goballistic.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.jei.category.sequencedAssembly.SequencedAssemblySubCategory;
import com.simibubi.create.content.processing.sequenced.SequencedRecipe;

public class GBSequencedAssemblySubCategories {
    public static class AssemblyStamping extends SequencedAssemblySubCategory {

        AnimatedStamp stamp;

        public AssemblyStamping() {
            super(25);
            stamp = new AnimatedStamp();
        }

        @Override
        public void draw(SequencedRecipe<?> recipe, PoseStack ms, double mouseX, double mouseY, int index) {
            stamp.offset = index;
            ms.pushPose();
            ms.translate(-5, 50, 0);
            ms.scale(.6f, .6f, .6f);
            stamp.draw(ms, getWidth() / 2, 0);
            ms.popPose();
        }

    }
}
