package com.jetpacker06.goballistic.content.bullet;

import net.minecraft.world.item.Item;

public abstract class AbstractAmmoItem extends Item implements BulletSpecific {
    public AbstractAmmoItem(Properties pProperties) {
        super(pProperties);
    }
}
