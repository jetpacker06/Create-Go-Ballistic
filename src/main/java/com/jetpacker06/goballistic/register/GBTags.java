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
        WRENCH_PICKUP(NameSpace.CREATE)

        ;
        public final TagKey<Block> tag;

        Blocks(NameSpace nameSpace, String tag) {
            ResourceLocation name = new ResourceLocation(nameSpace.id, "blocks/" + tag);
            this.tag = BlockTags.create(name);
        }

        Blocks(NameSpace nameSpace) {
            ResourceLocation name = new ResourceLocation(nameSpace.id, "blocks/" + this.name().toLowerCase());
            this.tag = BlockTags.create(name);
        }
    }

    public enum Items {
        BASALT(NameSpace.FORGE)

        ;
        public final TagKey<Item> tag;

        Items(NameSpace nameSpace, String tag) {
            ResourceLocation name = new ResourceLocation(nameSpace.id, "items/" + tag);
            this.tag = ItemTags.create(name);
        }

        Items(NameSpace nameSpace) {
            ResourceLocation name = new ResourceLocation(nameSpace.id, "items/" + this.name().toLowerCase());
            this.tag = ItemTags.create(name);
        }
    }
}
