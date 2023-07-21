package com.jetpacker06.goballistic.content.kinetics.stamp;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AngleHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
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
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);

        renderItem(be, partialTicks, ms, buffer, light, overlay);

        if (Backend.canUseInstancing(be.getLevel()))
            return;

        BlockState blockState = be.getBlockState();
        StampingBehavior stampingBehavior = be.getStampingBehavior();
        float renderedHeadOffset =
                stampingBehavior.getRenderedHeadOffset(partialTicks) * stampingBehavior.mode.headOffset;

        SuperByteBuffer headRender = CachedBufferer.partialFacing(AllPartialModels.MECHANICAL_PRESS_HEAD, blockState,
                blockState.getValue(HORIZONTAL_FACING));
        headRender.translate(0, -renderedHeadOffset, 0)
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid()));
    }
    protected void renderItem(MechanicalStampBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        if (be.heldItem.isEmpty()) return;

        BlockState deployerState = be.getBlockState();
      //  Vec3 offset = getHandOffset(be, partialTicks, deployerState).add(VecHelper.getCenterOf(BlockPos.ZERO));
        ms.pushPose();
      //  ms.translate(offset.x, offset.y, offset.z);

        Direction facing = deployerState.getValue(HORIZONTAL_FACING);

        float yRot = AngleHelper.horizontalAngle(facing) + 180;

        ms.mulPose(Vector3f.YP.rotationDegrees(yRot));

        ItemRenderer itemRenderer = Minecraft.getInstance()
                .getItemRenderer();

        ItemTransforms.TransformType transform = ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND;

        itemRenderer.renderStatic(be.heldItem, transform, light, overlay, ms, buffer, 0);
        ms.popPose();
    }

    @Override
    protected BlockState getRenderedBlockState(MechanicalStampBlockEntity be) {
        return shaft(getRotationAxisOf(be));
    }
}
