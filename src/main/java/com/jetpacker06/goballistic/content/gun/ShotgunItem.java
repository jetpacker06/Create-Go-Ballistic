package com.jetpacker06.goballistic.content.gun;

import com.jetpacker06.goballistic.content.bullet.BulletType;
import com.jetpacker06.goballistic.register.GBSoundEvents;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;

public class ShotgunItem extends AbstractGunItem {

    public ShotgunItem(Properties p_43009_) {
        super(p_43009_, BulletType.SHELL, 4);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 222;
    }

    @Override
    public @NotNull SoundEvent getFireSound() {
        return GBSoundEvents.SHOTGUN_SHOT.get();
    }
}
