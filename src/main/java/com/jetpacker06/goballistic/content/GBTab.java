package com.jetpacker06.goballistic.content;

import com.jetpacker06.goballistic.GoBallistic;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class GBTab {
    public static final CreativeModeTab GBT = new CreativeModeTab(GoBallistic.MOD_ID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.GUNPOWDER);
        }
    };
}
