package com.jetpacker06.goballistic.content.gun;

import com.jetpacker06.goballistic.content.bullet.BulletType;

public class FlintlockItem extends AbstractGunItem {

    public FlintlockItem(Properties p_43009_) {
        super(p_43009_, BulletType.MINIE_BALL, 1);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 12;
    }
}
