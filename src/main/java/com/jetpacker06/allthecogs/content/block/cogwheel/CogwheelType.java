package com.jetpacker06.allthecogs.content.block.cogwheel;

import com.jetpacker06.allthecogs.AllTheCogs;
import com.jetpacker06.allthecogs.registrate.ATCBlockEntityTypes;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

/**
 * A class to create and hold a small variant and large variant of a cogwheel type.
 */
public class CogwheelType {
    private final BlockEntry<? extends CustomCogwheelBlock> smallCog;
    private final BlockEntry<? extends CustomCogwheelBlock> largeCog;

    public CogwheelType(String cogwheelType, NonNullSupplier<? extends Block> blockProperties) {
        this(cogwheelType, new CogProperties(blockProperties).mineableAxe().mineablePickaxe());
    }

    public CogwheelType(String cogwheelType, CogProperties cogProperties) {
        var small = AllTheCogs.registrate()
                .block(cogwheelType + "_cogwheel", CustomCogwheelBlock::small)
                .initialProperties(cogProperties.blockProperties)
                .blockstate(BlockStateGen.axisBlockProvider(false))
                .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                .item(CogwheelBlockItem::new).build();
        var large = AllTheCogs.registrate()
                .block("large_" + cogwheelType + "_cogwheel", CustomCogwheelBlock::large)
                .initialProperties(cogProperties.blockProperties)
                .blockstate(BlockStateGen.axisBlockProvider(false))
                .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                .item(CogwheelBlockItem::new).build();

        small.transform(BlockStressDefaults.setImpact(cogProperties.stressRequiredSmall));
        large.transform(BlockStressDefaults.setImpact(cogProperties.stressRequiredLarge));

        if (cogProperties.mineableAxe) {
            small.transform(TagGen.axeOnly());
            large.transform(TagGen.axeOnly());
        }
        if (cogProperties.mineablePickaxe) {
            small.transform(TagGen.pickaxeOnly());
            large.transform(TagGen.pickaxeOnly());
        }
        if (cogProperties.mineableShovel) {
            small.transform(b -> b.tag(BlockTags.MINEABLE_WITH_SHOVEL));
            large.transform(b -> b.tag(BlockTags.MINEABLE_WITH_SHOVEL));
        }
        if (cogProperties.mineableHoe) {
            small.transform(b -> b.tag(BlockTags.MINEABLE_WITH_HOE));
            large.transform(b -> b.tag(BlockTags.MINEABLE_WITH_HOE));
        }
        this.smallCog = small.register();
        this.largeCog = large.register();

        ATCBlockEntityTypes.customCogwheels.add(this.smallCogEntry());
        ATCBlockEntityTypes.customCogwheels.add(this.largeCogEntry());
    }
    public BlockEntry<? extends CustomCogwheelBlock> smallCogEntry() {
        return this.smallCog;
    }
    public BlockEntry<? extends CustomCogwheelBlock> largeCogEntry() {
        return this.largeCog;
    }

    public static class CogProperties {
        private final NonNullSupplier<? extends Block> blockProperties;
        private double stressRequiredSmall = 0d;
        private double stressRequiredLarge = 0d;
        private boolean mineableAxe = false;
        private boolean mineablePickaxe = false;
        private boolean mineableShovel = false;
        private boolean mineableHoe = false;

        public CogProperties(NonNullSupplier<? extends Block> properties) {
            this.blockProperties = properties;
        }
        public CogProperties stress(double stress) {
            this.stressRequiredLarge = stress;
            this.stressRequiredSmall = stress;
            return this;
        }
        public CogProperties stressLarge(double stress) {
            this.stressRequiredLarge = stress;
            return this;
        }
        public CogProperties stressSmall(double stress) {
            this.stressRequiredSmall = stress;
            return this;
        }
        public CogProperties mineableAxe() {
            this.mineableAxe = true;
            return this;
        }
        public CogProperties mineablePickaxe() {
            this.mineablePickaxe = true;
            return this;
        }
        public CogProperties mineableShovel() {
            this.mineableShovel = true;
            return this;
        }
        public CogProperties mineableHoe() {
            this.mineableHoe = true;
            return this;
        }
    }
}
