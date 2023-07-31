package com.jetpacker06.goballistic.content.bullet.projectile;

import com.jetpacker06.goballistic.GoBallistic;
import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.utility.AngleHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.simibubi.create.content.equipment.potatoCannon.PotatoProjectileRenderMode.entityRandom;

public class BulletProjectileRenderer extends EntityRenderer<BulletProjectileEntity> {

    public BulletProjectileRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    @Override
    public void render(BulletProjectileEntity entity, float yaw, float pt, @NotNull PoseStack ms, @NotNull MultiBufferSource buffer,
                       int light) {
        ItemStack item = entity.getItem();
        if (item.isEmpty())
            return;
        ms.pushPose();
        ms.translate(0, entity.getBoundingBox()
                .getYsize() / 2 - 1 / 8f, 0);

        Minecraft mc = Minecraft.getInstance();
        Vec3 p1 = Objects.requireNonNull(mc.getCameraEntity())
                .getEyePosition(pt);
        Vec3 diff = entity.getBoundingBox()
                .getCenter()
                .subtract(p1);

        @SuppressWarnings("all")
        var x = TransformStack.cast(ms)
                .rotateY(AngleHelper.deg(Mth.atan2(diff.x, diff.z)) + 180)
                .rotateX(AngleHelper.deg(Mth.atan2(diff.y, Mth.sqrt((float) (diff.x * diff.x + diff.z * diff.z)))));
        TransformStack.cast(ms)
                .rotateZ((entity.tickCount + pt) * 2 * entityRandom(entity, 16))
                .rotateX((entity.tickCount + pt) * entityRandom(entity, 32));

        Minecraft.getInstance()
                .getItemRenderer()
                .renderStatic(item, ItemTransforms.TransformType.GROUND, light, OverlayTexture.NO_OVERLAY, ms, buffer, 0);
        ms.popPose();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BulletProjectileEntity pEntity) {
        return new ResourceLocation(GoBallistic.MOD_ID, "block/lead_block");
    }
}
