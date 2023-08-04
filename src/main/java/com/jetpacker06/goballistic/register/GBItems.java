package com.jetpacker06.goballistic.register;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.content.basic.item.BatGuanoItem;
import com.jetpacker06.goballistic.content.bullet.*;
import com.jetpacker06.goballistic.content.gun.FlintlockItem;
import com.jetpacker06.goballistic.content.gun.RevolverItem;
import com.jetpacker06.goballistic.content.gun.RifleItem;
import com.jetpacker06.goballistic.content.gun.ShotgunItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

public class GBItems {
    private static final CreateRegistrate REGISTRATE = GoBallistic.REGISTRATE;

    private static ItemEntry<Item> basic(String langName) {
        return basic(langName.replace(" ", "_").replace(".", "").toLowerCase(), langName);
    }

    private static ItemEntry<Item> basic(String registryName, String langName) {
        return REGISTRATE.item(registryName, Item::new)
                .lang(langName)
                .register();
    }

    public static final ItemEntry<Item> SALTPETER = basic("Saltpeter");
    public static final ItemEntry<BatGuanoItem> BAT_GUANO = REGISTRATE.item("bat_guano", BatGuanoItem::new)
            .lang("Bat Guano")
            .register();
    public static final ItemEntry<Item> SULFUR = basic("Sulfur");
    public static final ItemEntry<Item> LIMESTONE_CHUNKS = basic("Limestone Chunks");
    public static final ItemEntry<Item> LEAD_INGOT = basic("Lead Ingot");
    public static final ItemEntry<CartridgeItem> CARTRIDGE_44 = REGISTRATE.item("cartridge_44", p -> new CartridgeItem(p, BulletType.CAL_44))
            .lang(".44 Caliber Cartridge")
            .register();
    public static final ItemEntry<Item> CASING_44 = basic("casing_44", ".44 Caliber Bullet Casing");
    public static final ItemEntry<Item> FILLED_CASING_44 = basic("filled_casing_44", "Filled .44 Caliber Bullet Casing");
    public static final ItemEntry<Item> BULLET_44 = basic("bullet_44", ".44 Caliber Bullet");
    public static final ItemEntry<BlankCartridgeItem> BLANK_44 = REGISTRATE.item("blank_44", p -> new BlankCartridgeItem(p, BulletType.CAL_44))
            .lang(".44 Caliber Blank")
            .register();
    public static final ItemEntry<CartridgeItem> CARTRIDGE_22 = REGISTRATE.item("cartridge_22", p -> new CartridgeItem(p, BulletType.CAL_22))
            .lang(".22 Caliber Cartridge")
            .register();
    public static final ItemEntry<Item> CASING_22 = basic("casing_22", ".22 Caliber Bullet Casing");
    public static final ItemEntry<Item> FILLED_CASING_22 = basic("filled_casing_22", "Filled .22 Caliber Bullet Casing");
    public static final ItemEntry<Item> BULLET_22 = basic("bullet_22", ".22 Caliber Bullet");
    public static final ItemEntry<BlankCartridgeItem> BLANK_22 = REGISTRATE.item("blank_22", p -> new BlankCartridgeItem(p, BulletType.CAL_22))
            .lang(".22 Caliber Blank")
            .register();
    public static final ItemEntry<ShotgunShellItem> SHELL = REGISTRATE.item("shell", ShotgunShellItem::new)
            .lang("Shotgun Shell")
            .register();
    public static final ItemEntry<Item> CASING_SHELL = basic("casing_shell", "Shell Casing");
    public static final ItemEntry<Item> FILLED_CASING_SHELL = basic("filled_casing_shell", "Filled Shell Casing");
    public static final ItemEntry<Item> SHELL_PELLETS = basic("Shell Pellets");
    public static final ItemEntry<BlankCartridgeItem> BLANK_SHELL = REGISTRATE.item("shell_blank", p -> new BlankCartridgeItem(p, BulletType.SHELL))
            .lang("Shell Blank")
            .register();
    public static final ItemEntry<MinieBallItem> MINIE_BALL = REGISTRATE.item("minie_ball", MinieBallItem::new)
            .lang("Minie Ball")
            .register();
    public static final ItemEntry<Item> STAMP_22_CAL_CASING = basic("stamp_casing_22", ".22 Caliber Bullet Casing Stamp");
    public static final ItemEntry<Item> STAMP_44_CAL_CASING = basic("stamp_casing_44",".44 Caliber Bullet Casing Stamp");
    public static final ItemEntry<Item> STAMP_22_CAL_BULLET = basic("stamp_bullet_22", ".22 Caliber Bullet Stamp");
    public static final ItemEntry<Item> STAMP_44_CAL_BULLET = basic("stamp_bullet_44", ".44 Caliber Bullet Stamp");
    public static final ItemEntry<FlintlockItem> FLINTLOCK = REGISTRATE.item("flintlock", FlintlockItem::new)
            .lang("Flintlock")
            .model(AssetLookup.existingItemModel())
            .register();
    public static final ItemEntry<RifleItem> RIFLE = REGISTRATE.item("rifle", RifleItem::new)
            .lang("Rifle")
            .model(AssetLookup.existingItemModel())
            .register();
    public static final ItemEntry<ShotgunItem> SHOTGUN = REGISTRATE.item("shotgun", ShotgunItem::new)
            .model(AssetLookup.existingItemModel())
            .lang("Shotgun")
            .register();
    public static final ItemEntry<RevolverItem> REVOLVER = REGISTRATE.item("revolver", RevolverItem::new)
            .lang("Revolver")
            .model(AssetLookup.existingItemModel())
            .register();

    public static void load() {}
}
