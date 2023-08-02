package com.jetpacker06.goballistic.register;

import com.jetpacker06.goballistic.GoBallistic;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.FluidEntry;

public class GBFluids {
  //  public static ResourceLocation waterStill = new ResourceLocation("block/water_still");
  //  public static ResourceLocation waterFlow = new ResourceLocation("block/water_flow");

    public static FluidEntry<VirtualFluid> GUNPOWDER;

    public static void registerFluids(CreateRegistrate registrate) {
        GUNPOWDER = registrate.virtualFluid("gunpowder", GoBallistic.resource("fluid/gunpowder_still"), GoBallistic.resource("fluid/gunpowder_flow"))
                .lang("Gunpowder")
                .tag(AllTags.forgeFluidTag("gunpowder"))
                .register();
    }
}
