package com.jetpacker06.goballistic.datagen;

import com.jetpacker06.goballistic.GoBallistic;
import com.simibubi.create.foundation.data.CreateRegistrate;

public class GBLang {
    public static void addLang(CreateRegistrate REGISTRATE) {
        REGISTRATE.addRawLang("itemGroup." + GoBallistic.MOD_ID, GoBallistic.NAME);
    }
}