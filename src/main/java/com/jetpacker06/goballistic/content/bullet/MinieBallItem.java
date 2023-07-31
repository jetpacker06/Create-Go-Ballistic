package com.jetpacker06.goballistic.content.bullet;

import com.jetpacker06.goballistic.content.bullet.projectile.AbstractAmmoItem;
import net.minecraft.world.item.Item;

public class MinieBallItem extends AbstractAmmoItem implements BulletSpecific {

    public MinieBallItem(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BulletType getBulletType() {
        return BulletType.MINIE_BALL;
    }
}
