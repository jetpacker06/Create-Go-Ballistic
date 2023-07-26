package com.jetpacker06.goballistic.register;

import com.jetpacker06.goballistic.GoBallistic;
import com.simibubi.create.Create;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class GBTags {
    public enum NameSpace {
        MOD(GoBallistic.MOD_ID),
        CREATE(Create.ID),
        FORGE("forge"),
        MINECRAFT("minecraft"),

        ;
        public final String id;
        NameSpace(String id) {
            this.id = id;
        }
    }
    public enum Blocks {

        ;
        public final TagKey<Block> tagKey;

        Blocks(NameSpace nameSpace, String tag) {
            ResourceLocation name = new ResourceLocation(nameSpace.id, "blocks/" + tag);
            this.tagKey = BlockTags.create(name);
        }
    }

    public enum Items {
        BASALT(NameSpace.FORGE, "basalt")

        ;
        public final TagKey<Item> tagKey;

        Items(NameSpace nameSpace, String tag) {
            ResourceLocation name = new ResourceLocation(nameSpace.id, "items/" + tag);
            this.tagKey = ItemTags.create(name);
        }
    }
}
