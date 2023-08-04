package com.jetpacker06.goballistic.register;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.content.kinetics.stamp.MechanicalStampBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.material.MaterialColor;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class GBBlocks {
    private static final CreateRegistrate REGISTRATE = GoBallistic.REGISTRATE;

    public static final BlockEntry<MechanicalStampBlock> MECHANICAL_STAMP = REGISTRATE.block("mechanical_stamp", MechanicalStampBlock::new)
            .lang("Mechanical Stamp")
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().color(MaterialColor.PODZOL))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.horizontalBlockProvider(true))
            .transform(BlockStressDefaults.setImpact(8.0))
            .item(AssemblyOperatorBlockItem::new)
            .transform(customItemModel())
            .register();

    public static void load() {}
}
