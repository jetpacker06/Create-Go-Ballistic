package com.jetpacker06.goballistic.content.gun;

import com.jetpacker06.goballistic.content.bullet.BulletType;

public class RifleItem extends AbstractGunItem {

    public RifleItem(Properties p_43009_) {
        super(p_43009_, BulletType.CAL_44, 4);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 24;
    }
}
