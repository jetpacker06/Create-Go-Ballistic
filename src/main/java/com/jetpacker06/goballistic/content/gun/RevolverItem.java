package com.jetpacker06.goballistic.content.gun;

import com.jetpacker06.goballistic.content.bullet.BulletType;
import com.jetpacker06.goballistic.register.GBSoundEvents;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;

public class RevolverItem extends AbstractGunItem {

    public RevolverItem(Properties p_43009_) {
        super(p_43009_, BulletType.CAL_22, 6, 5);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 222;
    }

    @Override
    public @NotNull SoundEvent getFireSound() {
        return GBSoundEvents.REVOLVER_SHOT.get();
    }
}
