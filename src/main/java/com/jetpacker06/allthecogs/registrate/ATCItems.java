package com.jetpacker06.allthecogs.registrate;

import com.jetpacker06.allthecogs.content.ATCTab;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;

public class ATCItems {
    public static ItemEntry<Item> ANDESITE_ALLOY_NUGGET;

    public static void registerItems(CreateRegistrate REGISTRATE) {
        REGISTRATE.creativeModeTab(() -> ATCTab.ATC);

    //    ANDESITE_ALLOY_NUGGET = REGISTRATE.item("andesite_alloy_nugget", Item::new).register();
    }
}
