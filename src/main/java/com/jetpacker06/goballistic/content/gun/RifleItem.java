package com.jetpacker06.goballistic.content.gun;

import com.jetpacker06.goballistic.content.bullet.BulletType;
import com.jetpacker06.goballistic.register.GBSoundEvents;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;

public class RifleItem extends AbstractGunItem {

    public RifleItem(Properties p_43009_) {
        super(p_43009_, BulletType.CAL_44, 4);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 24;
    }

    @Override
    public @NotNull SoundEvent getFireSound() {
        return GBSoundEvents.RIFLE_SHOT.get();
    }
}
