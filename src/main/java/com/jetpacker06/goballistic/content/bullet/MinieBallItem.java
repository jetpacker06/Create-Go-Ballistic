package com.jetpacker06.goballistic.content.bullet;

public class MinieBallItem extends AbstractAmmoItem {
    public MinieBallItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BulletType getBulletType() {
        return BulletType.MINIE_BALL;
    }
}
