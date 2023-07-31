package com.jetpacker06.goballistic.content.gun;

import com.jetpacker06.goballistic.content.bullet.BulletType;

public class RevolverItem extends AbstractGunItem {

    public RevolverItem(Properties p_43009_) {
        super(p_43009_, BulletType.CAL_22, 6);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 222;
    }
}
