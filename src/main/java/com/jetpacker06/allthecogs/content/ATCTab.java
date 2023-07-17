package com.jetpacker06.allthecogs.content;

import com.jetpacker06.allthecogs.AllTheCogs;
import com.jetpacker06.allthecogs.registrate.ATCItems;
import com.simibubi.create.AllItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class ATCTab {
    public static final CreativeModeTab ATC = new CreativeModeTab(AllTheCogs.MOD_ID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.EGG);
        }
    };
}
