package com.jetpacker06.allthecogs.content.block.cogwheel;

import com.jetpacker06.allthecogs.registrate.ATCBlockEntityTypes;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CustomCogwheelBlock extends CogWheelBlock {

    private CustomCogwheelBlock(boolean large, Properties properties) {
        super(large, properties);
    }
    public static CustomCogwheelBlock small(Properties properties) {
        return new CustomCogwheelBlock(false, properties);
    }
    public static CustomCogwheelBlock large(Properties properties) {
        return new CustomCogwheelBlock(true, properties);
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return ATCBlockEntityTypes.CUSTOM_COGWHEEL.get();
    }
}