package com.jetpacker06.goballistic.register;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.content.bullet.projectile.BulletProjectileEntity;
import com.jetpacker06.goballistic.content.bullet.projectile.BulletProjectileRenderer;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.EntityEntry;
import net.minecraft.world.entity.MobCategory;

public class GBEntities {
    private static final CreateRegistrate REGISTRATE = GoBallistic.REGISTRATE;

    public static final EntityEntry<BulletProjectileEntity.RifleShot> RIFLE_SHOT = REGISTRATE.entity("rifle_shot", BulletProjectileEntity.RifleShot::new, MobCategory.MISC)
            .properties(p -> p.setTrackingRange(4)
                    .setUpdateInterval(20)
                    .setShouldReceiveVelocityUpdates(true))
            .lang("Rifle Shot")
            .renderer(() -> BulletProjectileRenderer::new)
            .register();
    public static final EntityEntry<BulletProjectileEntity.FlintlockShot> FLINTLOCK_SHOT = REGISTRATE.entity("flintlock_shot", BulletProjectileEntity.FlintlockShot::new, MobCategory.MISC)
            .properties(p -> p.setTrackingRange(4)
                    .setUpdateInterval(20)
                    .setShouldReceiveVelocityUpdates(true))
            .lang("Flintlock Shot")
            .renderer(() -> BulletProjectileRenderer::new)
            .register();
    public static final EntityEntry<BulletProjectileEntity.RevolverShot> REVOLVER_SHOT = REGISTRATE.entity("revolver_shot", BulletProjectileEntity.RevolverShot::new, MobCategory.MISC)
            .properties(p -> p.setTrackingRange(4)
                    .setUpdateInterval(20)
                    .setShouldReceiveVelocityUpdates(true))
            .lang("Revolver Shot")
            .renderer(() -> BulletProjectileRenderer::new)
            .register();
  //  public static final EntityEntry<BulletProjectileEntity.ShotgunShot> SHOTGUN_SHOT = REGISTRATE.entity("shotgun_shot", BulletProjectileEntity.ShotgunShot::new, MobCategory.MISC)
  //          .properties(p -> p.setTrackingRange(4)
  //                  .setUpdateInterval(20)
  //                  .setShouldReceiveVelocityUpdates(true))
  //          .lang("Shotgun Pellet")
  //          .renderer(() -> BulletProjectileRenderer::new)
  //          .register();

    public static void load() {}
}
