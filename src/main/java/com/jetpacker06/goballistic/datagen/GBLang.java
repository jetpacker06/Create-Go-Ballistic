package com.jetpacker06.goballistic.datagen;

import com.jetpacker06.goballistic.GoBallistic;
import com.simibubi.create.foundation.data.CreateRegistrate;

public class GBLang {
    private static final String id = GoBallistic.MOD_ID;
    private static final String name = GoBallistic.NAME;

    public static void add(String key, String value) {
        GoBallistic.REGISTRATE.addRawLang(key, value);
    }

    public static void register() {
        add("itemGroup." + id, name);
        add(id + ".recipe.stamping", "Stamping");
        add(id + ".recipe.assembling.stamping_item", "Process with Stamp");
    }
}