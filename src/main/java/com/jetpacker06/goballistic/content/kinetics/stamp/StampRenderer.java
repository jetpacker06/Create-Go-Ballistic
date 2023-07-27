package com.jetpacker06.goballistic.content.kinetics.stamp;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class StampRenderer extends KineticBlockEntityRenderer<MechanicalStampBlockEntity> {

    public StampRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRenderOffScreen(@NotNull MechanicalStampBlockEntity be) {
        return true;
    }

    @Override
    protected void renderSafe(MechanicalStampBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        StampingBehavior stampingBehavior = be.getStampingBehavior();
        float renderedHeadOffset =
                -stampingBehavior.getRenderedHeadOffset(partialTicks) * StampingBehavior.Mode.headOffset;

        renderItem(be, ms, buffer, light, overlay, renderedHeadOffset);

        if (Backend.canUseInstancing(be.getLevel()))
            return;

        BlockState blockState = be.getBlockState();

        SuperByteBuffer headRender = CachedBufferer.partialFacing(AllPartialModels.MECHANICAL_PRESS_HEAD, blockState,
                blockState.getValue(HORIZONTAL_FACING));
        headRender.translate(0, renderedHeadOffset, 0)
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid()));
    }

    protected void renderItem(MechanicalStampBlockEntity be, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay, float headOffset) {
        if (be.heldItem.isEmpty()) return;

        ms.pushPose();
        ms.translate(.5, headOffset + .05f, .68 - (6f/16f));
        float xRot = 90;

        ms.mulPose(Vector3f.XP.rotationDegrees(xRot));

        ItemRenderer itemRenderer = Minecraft.getInstance()
                .getItemRenderer();

        itemRenderer.renderStatic(be.heldItem, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, light, overlay, ms, buffer, 0);
        ms.popPose();
    }

    @Override
    protected BlockState getRenderedBlockState(MechanicalStampBlockEntity be) {
        return shaft(getRotationAxisOf(be));
    }
}
