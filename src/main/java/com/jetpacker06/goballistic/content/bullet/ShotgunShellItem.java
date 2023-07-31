package com.jetpacker06.goballistic.content.bullet;

import com.jetpacker06.goballistic.content.bullet.projectile.AbstractAmmoItem;

public class ShotgunShellItem extends AbstractAmmoItem implements BulletSpecific {
    public ShotgunShellItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BulletType getBulletType() {
        return BulletType.SHELL;
    }
}
