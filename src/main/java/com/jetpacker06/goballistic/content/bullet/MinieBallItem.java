package com.jetpacker06.goballistic.content.bullet;

import net.minecraft.world.item.Item;

public class MinieBallItem extends Item implements IBulletType {

    public MinieBallItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BulletType getBulletType() {
        return BulletType.MINIE_BALL;
    }
}
