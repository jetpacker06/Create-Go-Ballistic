package com.jetpacker06.goballistic.content.gun;

import com.jetpacker06.goballistic.content.bullet.BulletType;

public class ShotgunItem extends AbstractGunItem {

    public ShotgunItem(Properties p_43009_) {
        super(p_43009_, BulletType.SHELL, 4);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 222;
    }
}
