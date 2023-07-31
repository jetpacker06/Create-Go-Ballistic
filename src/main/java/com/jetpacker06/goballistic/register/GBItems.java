package com.jetpacker06.goballistic.register;

import com.jetpacker06.goballistic.content.basic.item.BatGuanoItem;
import com.jetpacker06.goballistic.content.bullet.*;
import com.jetpacker06.goballistic.content.gun.FlintlockItem;
import com.jetpacker06.goballistic.content.gun.RevolverItem;
import com.jetpacker06.goballistic.content.gun.RifleItem;
import com.jetpacker06.goballistic.content.gun.ShotgunItem;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

public class GBItems {
    private static CreateRegistrate registrate;

    public static ItemEntry<Item> SALTPETER;
    public static ItemEntry<BatGuanoItem> BAT_GUANO;
    public static ItemEntry<Item> SULFUR;
    public static ItemEntry<Item> LIMESTONE_CHUNKS;
    public static ItemEntry<Item> LEAD_INGOT;
    public static ItemEntry<CartridgeItem> CARTRIDGE_44;
    public static ItemEntry<Item> CASING_44;
    public static ItemEntry<Item> FILLED_CASING_44;
    public static ItemEntry<Item> BULLET_44;
    public static ItemEntry<BlankCartridgeItem> BLANK_44;
    public static ItemEntry<CartridgeItem> CARTRIDGE_22;
    public static ItemEntry<Item> CASING_22;
    public static ItemEntry<Item> FILLED_CASING_22;
    public static ItemEntry<Item> BULLET_22;
    public static ItemEntry<BlankCartridgeItem> BLANK_22 ;
    public static ItemEntry<ShotgunShellItem> SHELL;
    public static ItemEntry<Item> CASING_SHELL;
    public static ItemEntry<Item> FILLED_CASING_SHELL;
    public static ItemEntry<Item> SHELL_PELLETS;
    public static ItemEntry<BlankCartridgeItem> BLANK_SHELL;
    public static ItemEntry<MinieBallItem> MINIE_BALL;
    public static ItemEntry<Item> STAMP_22_CAL_CASING;
    public static ItemEntry<Item> STAMP_44_CAL_CASING;
    public static ItemEntry<Item> STAMP_22_CAL_BULLET;
    public static ItemEntry<Item> STAMP_44_CAL_BULLET;

    public static ItemEntry<FlintlockItem> FLINTLOCK;
    public static ItemEntry<RifleItem> RIFLE;
    public static ItemEntry<ShotgunItem> SHOTGUN;
    public static ItemEntry<RevolverItem> REVOLVER;

    private static ItemEntry<Item> basic(String langName) {
        return basic(langName.replace(" ", "_").replace(".", "").toLowerCase(), langName);
    }

    private static ItemEntry<Item> basic(String registryName, String langName) {
        return registrate.item(registryName, Item::new)
                .lang(langName)
                .register();
    }

    public static void registerItems(CreateRegistrate REGISTRATE) {
        registrate = REGISTRATE;

        SALTPETER = basic("Saltpeter");
        BAT_GUANO = REGISTRATE.item("bat_guano", BatGuanoItem::new)
                .lang("Bat Guano")
                .register();
        SULFUR = basic("Sulfur");
        LIMESTONE_CHUNKS = basic("Limestone Chunks");
        LEAD_INGOT = basic("Lead Ingot");
        CARTRIDGE_44 = REGISTRATE.item("cartridge_44", p -> new CartridgeItem(p, BulletType.CAL_44))
                .lang(".44 Caliber Cartridge")
                .register();
        CASING_44 = basic("casing_44", ".44 Caliber Bullet Casing");
        FILLED_CASING_44 = basic("filled_casing_44", "Filled .44 Caliber Bullet Casing");
        BULLET_44 = basic("bullet_44", ".44 Caliber Bullet");
        BLANK_44 = REGISTRATE.item("blank_44", p -> new BlankCartridgeItem(p, BulletType.CAL_44))
                .lang(".44 Caliber Blank")
                .register();
        CARTRIDGE_22 = REGISTRATE.item("cartridge_22", p -> new CartridgeItem(p, BulletType.CAL_22))
                .lang(".22 Caliber Cartridge")
                .register();
        CASING_22= basic("casing_22", ".22 Caliber Bullet Casing");
        FILLED_CASING_22 = basic("filled_casing_22", "Filled .22 Caliber Bullet Casing");
        BULLET_22 = basic("bullet_22", ".22 Caliber Bullet");
        BLANK_22 = REGISTRATE.item("blank_22", p -> new BlankCartridgeItem(p, BulletType.CAL_22))
                .lang(".22 Caliber Blank")
                .register();
        SHELL = REGISTRATE.item("shell", ShotgunShellItem::new)
                .lang("Shotgun Shell")
                .register();
        FILLED_CASING_SHELL = basic("filled_casing_shell", "Filled Shell Casing");
        CASING_SHELL = basic("casing_shell", "Shell Casing");
        SHELL_PELLETS = basic("Shell Pellets");
        BLANK_SHELL = REGISTRATE.item("shell_blank", p -> new BlankCartridgeItem(p, BulletType.SHELL))
                .lang("Shell Blank")
                .register();
        MINIE_BALL = REGISTRATE.item("minie_ball", MinieBallItem::new)
                .lang("Minie Ball")
                .register();
        STAMP_44_CAL_CASING = basic("stamp_casing_44",".44 Caliber Bullet Casing Stamp");
        STAMP_44_CAL_BULLET = basic("stamp_bullet_44", ".44 Caliber Bullet Stamp");
        STAMP_22_CAL_CASING = basic("stamp_casing_22", ".22 Caliber Bullet Casing Stamp");
        STAMP_22_CAL_BULLET = basic("stamp_bullet_22", ".22 Caliber Bullet Stamp");

        FLINTLOCK = REGISTRATE.item("flintlock", FlintlockItem::new)
                .lang("Flintlock")
                .register();
        RIFLE = REGISTRATE.item("rifle", RifleItem::new)
                .lang("Rifle")
                .register();
        SHOTGUN = REGISTRATE.item("shotgun", ShotgunItem::new)
                .lang("Shotgun")
                .register();
        REVOLVER = REGISTRATE.item("revolver", RevolverItem::new)
                .lang("Revolver")
                .register();
    }
}
