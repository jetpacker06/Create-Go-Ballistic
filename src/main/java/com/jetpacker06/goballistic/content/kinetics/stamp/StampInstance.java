package com.jetpacker06.goballistic.content.kinetics.stamp;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.api.instance.DynamicInstance;
import com.jozufozu.flywheel.core.Materials;
import com.jozufozu.flywheel.core.materials.oriented.OrientedData;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.ShaftInstance;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlock;
import com.simibubi.create.foundation.utility.AngleHelper;
import com.simibubi.create.foundation.utility.AnimationTickHolder;

public class StampInstance extends ShaftInstance<MechanicalStampBlockEntity> implements DynamicInstance {
    private final OrientedData pressHead;

    public StampInstance(MaterialManager materialManager, MechanicalStampBlockEntity blockEntity) {
        super(materialManager, blockEntity);

        pressHead = materialManager.defaultSolid()
                .material(Materials.ORIENTED)
                .getModel(AllPartialModels.MECHANICAL_PRESS_HEAD, blockState)
                .createInstance();

        Quaternion q = Vector3f.YP
                .rotationDegrees(AngleHelper.horizontalAngle(blockState.getValue(MechanicalPressBlock.HORIZONTAL_FACING)));

        pressHead.setRotation(q);

        transformModels();
    }

    @Override
    public void beginFrame() {
        transformModels();
    }

    private void transformModels() {
        float renderedHeadOffset = getRenderedHeadOffset(blockEntity);

        pressHead.setPosition(getInstancePosition())
                .nudge(0, -renderedHeadOffset, 0);
    }

    private float getRenderedHeadOffset(MechanicalStampBlockEntity press) {
        StampingBehavior pressingBehaviour = press.getStampingBehavior();
        return pressingBehaviour.getRenderedHeadOffset(AnimationTickHolder.getPartialTicks())
                * pressingBehaviour.mode.headOffset;
    }

    @Override
    public void updateLight() {
        super.updateLight();

        relight(pos, pressHead);
    }

    @Override
    public void remove() {
        super.remove();
        pressHead.delete();
    }
}
