package com.jetpacker06.goballistic.content.gun;

import com.jetpacker06.goballistic.content.bullet.BulletType;
import com.jetpacker06.goballistic.register.GBSoundEvents;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;

public class FlintlockItem extends AbstractGunItem {

    public FlintlockItem(Properties p_43009_) {
        super(p_43009_, BulletType.MINIE_BALL, 1, 30);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 12;
    }

    @Override
    public @NotNull SoundEvent getFireSound() {
        return GBSoundEvents.FLINTLOCK_SHOT.get();
    }
}
