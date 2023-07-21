package com.jetpacker06.goballistic.register;

import com.jetpacker06.goballistic.content.GBTab;
import com.simibubi.create.foundation.data.CreateRegistrate;

public class GBItems {

    public static void registerItems(CreateRegistrate REGISTRATE) {
        REGISTRATE.creativeModeTab(() -> GBTab.GBT);
    }
}
