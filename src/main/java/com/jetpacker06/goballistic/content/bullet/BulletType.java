package com.jetpacker06.goballistic.content.bullet;

import com.jetpacker06.goballistic.content.bullet.projectile.BulletProjectileEntity;
import com.jetpacker06.goballistic.register.GBEntities;
import com.jetpacker06.goballistic.register.GBItems;
import com.tterrag.registrate.util.entry.EntityEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonnullType;
import net.minecraft.world.entity.EntityType;

public enum BulletType {
    CAL_22(GBItems.CARTRIDGE_22, GBEntities.REVOLVER_SHOT, new Properties().damage(6)),
    CAL_44(GBItems.CARTRIDGE_44, GBEntities.RIFLE_SHOT, new Properties().damage(8)),
  //  SHELL(GBItems.SHELL, GBEntities.SHOTGUN_SHOT, new Properties().damage(12).spread(5)),
    MINIE_BALL(GBItems.MINIE_BALL, GBEntities.FLINTLOCK_SHOT, new Properties().damage(12));


    final ItemEntry<? extends AbstractAmmoItem> ammoItem;
    final EntityEntry<? extends BulletProjectileEntity> entityEntry;
    final float drag;
    final int spread;
    final float damage;
    final float knockback;
    BulletType(ItemEntry<? extends AbstractAmmoItem> ammoItem, EntityEntry<? extends BulletProjectileEntity> entityEntry, Properties properties) {
        this.ammoItem = ammoItem;
        this.entityEntry = entityEntry;
        this.drag = properties.drag;
        this.spread = properties.spread;
        this.damage = properties.damage;
        this.knockback = properties.knockback;
    }

    public float getDrag() {
        return this.drag;
    }

    public float getDamage() {
        return this.damage;
    }

    public int getSpread() {
        return this.spread;
    }

    public AbstractAmmoItem getAmmoItem() {
        return this.ammoItem.get();
    }

    public ItemEntry<? extends AbstractAmmoItem> getAmmoItemEntry() {
        return this.ammoItem;
    }

    public @NonnullType EntityType<? extends BulletProjectileEntity> getEntity() {
        return this.getEntityEntry().get();
    }

    public EntityEntry<? extends BulletProjectileEntity> getEntityEntry() {
        return this.entityEntry;
    }

    private static class Properties {
        private int spread = 1;
        private float drag = 0.99f;
        private float damage = 8;
        private float knockback = 1;

        public Properties spread(int spread) {
            this.spread = spread;
            return this;
        }

        public Properties drag(float drag) {
            this.drag = drag;
            return this;
        }

        public Properties damage(float damage) {
            this.damage = damage;
            return this;
        }

        public Properties knockback(float knockback) {
            this.knockback = knockback;
            return this;
        }
    }
}
