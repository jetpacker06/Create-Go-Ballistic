package com.jetpacker06.goballistic.content.bullet;

public class ShotgunShellItem extends AbstractAmmoItem {
    public ShotgunShellItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BulletType getBulletType() {
        return BulletType.SHELL;
    }
}
