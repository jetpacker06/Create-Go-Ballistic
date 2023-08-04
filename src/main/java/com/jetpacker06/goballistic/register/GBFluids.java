package com.jetpacker06.goballistic.register;

import com.jetpacker06.goballistic.GoBallistic;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.FluidEntry;

public class GBFluids {
    private static final CreateRegistrate REGISTRATE = GoBallistic.REGISTRATE;

    public static final FluidEntry<VirtualFluid> GUNPOWDER = REGISTRATE.virtualFluid("gunpowder",
                    GoBallistic.resource("fluid/gunpowder_still"), GoBallistic.resource("fluid/gunpowder_flow"))
            .lang("Gunpowder")
            .tag(AllTags.forgeFluidTag("gunpowder"))
            .register();

    public static void load() {}
}
