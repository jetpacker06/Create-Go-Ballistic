package com.jetpacker06.goballistic.datagen;

import com.jetpacker06.goballistic.register.GBTags;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class GBTagGen {
    public static void datagen(CreateRegistrate REGISTRATE) {
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, GBTagGen::genBlockTags);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, GBTagGen::genItemTags);
    }

    private static void genBlockTags(RegistrateTagsProvider<Block> prov) {
        //prov.tag(GBTags.Blocks.WRENCH_PICKUP.tag).add();
    }

    private static void genItemTags(RegistrateTagsProvider<Item> prov) {
        prov.tag(GBTags.Items.BASALT.tag).add(Items.BASALT, Items.POLISHED_BASALT, Items.SMOOTH_BASALT);
    }
}
