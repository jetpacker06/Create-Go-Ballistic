package com.jetpacker06.allthecogs;

import com.jetpacker06.allthecogs.registrate.ATCBlockEntityTypes;
import com.jetpacker06.allthecogs.registrate.ATCCogwheelTypes;
import com.jetpacker06.allthecogs.registrate.ATCItems;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AllTheCogs.MOD_ID)
public class AllTheCogs {
    public static final String MOD_ID = "allthecogs";
    public static final String NAME = "All The Cogs";

    private static final NonNullSupplier<CreateRegistrate> createRegistrate = NonNullSupplier
            .lazy(() -> CreateRegistrate.create(MOD_ID).registerEventListeners(FMLJavaModLoadingContext.get()
                    .getModEventBus()));
    public static CreateRegistrate registrate() {
        return createRegistrate.get();
    }

    public AllTheCogs() {
        ATCItems.registerItems(registrate());
        ATCCogwheelTypes.registerCogwheelTypes();
        ATCBlockEntityTypes.registerBlockEntities(registrate());
        MinecraftForge.EVENT_BUS.register(this);
    }
}
