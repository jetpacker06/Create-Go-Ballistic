package com.jetpacker06.goballistic.content.bullet;

import net.minecraft.world.item.Item;

public class CartridgeItem extends Item implements IBulletType {
    private final BulletType bulletType;

    public CartridgeItem(Properties pProperties, BulletType bulletType) {
        super(pProperties);
        this.bulletType = bulletType;
    }

    @Override
    public BulletType getBulletType() {
        return this.bulletType;
    }
}
