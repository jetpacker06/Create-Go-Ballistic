package com.jetpacker06.goballistic.register;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.resources.ResourceLocation;

public class GBFluids {
    public static ResourceLocation waterStill = new ResourceLocation("block/water_still");
    public static ResourceLocation waterFlow = new ResourceLocation("block/water_flow");

    public static FluidEntry<VirtualFluid> GUNPOWDER;

    public static void registerFluids(CreateRegistrate registrate) {
        GUNPOWDER = registrate.virtualFluid("gunpowder", waterStill, waterFlow)
                .lang("Gunpowder")
                .tag(AllTags.forgeFluidTag("gunpowder"))
                .register();
    }
}
