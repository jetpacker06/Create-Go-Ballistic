package com.jetpacker06.goballistic;

import com.jetpacker06.goballistic.datagen.GBLang;
import com.jetpacker06.goballistic.register.GBBlockEntities;
import com.jetpacker06.goballistic.register.GBBlocks;
import com.jetpacker06.goballistic.register.GBItems;
import com.jetpacker06.goballistic.register.GBRecipeTypes;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GoBallistic.MOD_ID)
public class GoBallistic {
    public static final String MOD_ID = "goballistic";
    public static final String NAME = "Create: Go Ballistic";

    private static final NonNullSupplier<CreateRegistrate> createRegistrate = NonNullSupplier
            .lazy(() -> CreateRegistrate.create(MOD_ID).registerEventListeners(FMLJavaModLoadingContext.get()
                    .getModEventBus()));
    public static CreateRegistrate registrate() {
        return createRegistrate.get();
    }

    public GoBallistic() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GBItems.registerItems(registrate());
        GBBlocks.registerBlocks(registrate());
        GBBlockEntities.registerBlockEntities(registrate());

        GBRecipeTypes.Registers.register(eventBus);

        GBLang.addLang(registrate());

        MinecraftForge.EVENT_BUS.register(this);
    }
}
