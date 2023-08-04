package com.jetpacker06.goballistic.register;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.content.kinetics.stamp.MechanicalStampBlockEntity;
import com.jetpacker06.goballistic.content.kinetics.stamp.StampRenderer;
import com.jetpacker06.goballistic.content.kinetics.stamp.StampInstance;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class GBBlockEntities {
    private static final CreateRegistrate REGISTRATE = GoBallistic.REGISTRATE;

    public static final BlockEntityEntry<MechanicalStampBlockEntity> MECHANICAL_STAMP = REGISTRATE.blockEntity("mechanical_stamp", MechanicalStampBlockEntity::new)
            .instance(() -> StampInstance::new)
            .validBlock(GBBlocks.MECHANICAL_STAMP)
            .renderer(() -> StampRenderer::new)
            .register();

    public static void load() {}
}
