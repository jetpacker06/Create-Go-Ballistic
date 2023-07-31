package com.jetpacker06.goballistic.content.bullet;

import com.jetpacker06.goballistic.content.bullet.projectile.AbstractAmmoItem;
import net.minecraft.world.item.Item;

public class CartridgeItem extends AbstractAmmoItem implements BulletSpecific {
    private final BulletType bulletType;

    public CartridgeItem(Item.Properties pProperties, BulletType bulletType) {
        super(pProperties);
        this.bulletType = bulletType;
    }

    @Override
    public BulletType getBulletType() {
        return this.bulletType;
    }
}
