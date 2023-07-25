package com.jetpacker06.goballistic.content.bullet;

import net.minecraft.world.item.Item;

public class ShotgunShellItem extends Item implements IBulletType {
    public ShotgunShellItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BulletType getBulletType() {
        return BulletType.SHELL;
    }
}
