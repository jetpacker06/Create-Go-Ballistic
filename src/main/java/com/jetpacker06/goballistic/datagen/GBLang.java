package com.jetpacker06.goballistic.datagen;

import com.jetpacker06.goballistic.GoBallistic;

public class GBLang {
    private static final String id = GoBallistic.MOD_ID;
    private static final String name = GoBallistic.NAME;

    public static void add(String key, String value) {
        GoBallistic.REGISTRATE.addRawLang(key, value);
    }

    public static void load() {
        add("itemGroup." + id, name);
        add(id + ".recipe.stamping", "Stamping");
        add(id + ".recipe.assembling.stamping_item", "Process with Stamp");
    }
}