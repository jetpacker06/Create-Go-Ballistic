package com.jetpacker06.goballistic.content.bullet;

import net.minecraft.world.item.Item;

public class BlankCartridgeItem extends Item implements BulletSpecific {
    private final BulletType bulletType;

    public BlankCartridgeItem(Properties pProperties, BulletType bulletType) {
        super(pProperties);
        this.bulletType = bulletType;
    }

    @Override
    public BulletType getBulletType() {
        return this.bulletType;
    }
}
