package com.jetpacker06.goballistic.register;

import com.jetpacker06.goballistic.content.kinetics.stamp.MechanicalStampBlockEntity;
import com.jetpacker06.goballistic.content.kinetics.stamp.StampRenderer;
import com.jetpacker06.goballistic.content.kinetics.stamp.StampInstance;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class GBBlockEntities {
    public static BlockEntityEntry<MechanicalStampBlockEntity> MECHANICAL_STAMP;

    public static void registerBlockEntities(CreateRegistrate REGISTRATE) {
        MECHANICAL_STAMP = REGISTRATE.blockEntity("mechanical_stamp", MechanicalStampBlockEntity::new)
                .instance(() -> StampInstance::new)
                .validBlock(GBBlocks.MECHANICAL_STAMP)
                .renderer(() -> StampRenderer::new)
                .register();
    }
}
