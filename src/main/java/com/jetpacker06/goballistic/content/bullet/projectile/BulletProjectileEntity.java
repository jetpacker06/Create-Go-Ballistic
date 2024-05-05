package com.jetpacker06.goballistic.content.bullet.projectile;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.content.bullet.BulletType;
import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.particle.AirParticleData;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class BulletProjectileEntity extends AbstractHurtingProjectile {

    private BulletType bulletType;

    public BulletProjectileEntity(EntityType<? extends BulletProjectileEntity> pType, Level pLevel, BulletType bulletType) {
        super(pType, pLevel);
        this.bulletType = bulletType;
    }

    public BulletType getBulletType() {
        return bulletType;
    }

    public void setBulletType(BulletType type) {
        bulletType = type;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        bulletType = BulletType.valueOf(nbt.getString("BulletType"));
        super.readAdditionalSaveData(nbt);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putString("BulletType", bulletType.name());
        super.addAdditionalSaveData(nbt);
    }

    @Override
    protected float getInertia() {
        return 1;
    }

    @Override
    protected @NotNull ParticleOptions getTrailParticle() {
        return new AirParticleData(1, 10);
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    public void tick() {
        setDeltaMovement(getDeltaMovement().add(0, -0.05/* * gravityMultiplier*/, 0)
                .scale(bulletType.getDrag()));
        super.tick();
    }

    public ItemStack getItem() {
        return new ItemStack(bulletType.getAmmoItem());
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult ray) {
        super.onHitEntity(ray);

        Vec3 hit = ray.getLocation();
        Entity target = ray.getEntity();
        Entity owner = this.getOwner();

        if (target instanceof BulletProjectileEntity)
            return;
        if (!target.isAlive())
            return;
        if (owner instanceof LivingEntity)
            ((LivingEntity) owner).setLastHurtMob(target);

        pop(hit);

        if (target instanceof WitherBoss && ((WitherBoss) target).isPowered())
            return;

        boolean targetIsEnderman = target.getType() == EntityType.ENDERMAN;
        int k = target.getRemainingFireTicks();
        if (this.isOnFire() && !targetIsEnderman)
            target.setSecondsOnFire(5);

        boolean onServer = !level.isClientSide;
        if (onServer && !target.hurt(doDamage(), this.getDamage())) {
            target.setRemainingFireTicks(k);
            kill();
            return;
        }

        if (targetIsEnderman)
            return;

        if (!(target instanceof LivingEntity livingentity)) {
            kill();
            return;
        }

        if (onServer) {
            Vec3 appliedMotion = this.getDeltaMovement()
                    .multiply(1.0D, 0.0D, 1.0D)
                    .normalize()
                    .scale(0.6);
            if (appliedMotion.lengthSqr() > 0.0D)
                livingentity.push(appliedMotion.x, 0.1D, appliedMotion.z);
        }

        if (onServer && owner instanceof LivingEntity) {
            EnchantmentHelper.doPostHurtEffects(livingentity, owner);
            EnchantmentHelper.doPostDamageEffects((LivingEntity) owner, livingentity);
        }

        if (livingentity != owner && livingentity instanceof Player && owner instanceof ServerPlayer
                && !this.isSilent()) {
            ((ServerPlayer) owner).connection
                    .send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
        }

        if (onServer && owner instanceof ServerPlayer serverPlayer) {
            if (!target.isAlive() && target.getType()
                    .getCategory() == MobCategory.MONSTER || (target instanceof Player && target != owner))
                AllAdvancements.POTATO_CANNON.awardTo(serverPlayer);
        }
        kill();
    }

    @Override
    protected void onHitBlock(BlockHitResult ray) {
        Vec3 hit = ray.getLocation();
        pop(hit);
        super.onHitBlock(ray);
        kill();
    }

    private void pop(Vec3 hit) {
        for (int i = 0; i < 7; i++) {
            Vec3 m = VecHelper.offsetRandomly(Vec3.ZERO, this.random, .25f);
            level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(this.bulletType.getAmmoItem())), hit.x, hit.y, hit.z, m.x, m.y,
                    m.z);
        }
    }

    public float getDamage() {
        return this.bulletType.getDamage();
    }

    public static class BulletDamageSource extends IndirectEntityDamageSource {
        public BulletDamageSource(Entity source, @Nullable Entity trueSource) {
            super(GoBallistic.MOD_ID + ".bullet", source, trueSource);
        }
    }

    private DamageSource doDamage() {
        return new BulletDamageSource(this, getOwner()).setProjectile();
    }






    public static class RifleShot extends BulletProjectileEntity {
        public RifleShot(EntityType<? extends BulletProjectileEntity> pType, Level pLevel) {
            super(pType, pLevel, BulletType.CAL_44);
        }
    }
    public static class FlintlockShot extends BulletProjectileEntity {
        public FlintlockShot(EntityType<? extends BulletProjectileEntity> pType, Level pLevel) {
            super(pType, pLevel, BulletType.MINIE_BALL);
        }
    }
 //   public static class ShotgunShot extends BulletProjectileEntity {
 //       public ShotgunShot(EntityType<? extends BulletProjectileEntity> pType, Level pLevel) {
 //           super(pType, pLevel, BulletType.SHELL);
 //       }
 //   }
    public static class RevolverShot extends BulletProjectileEntity {
        public RevolverShot(EntityType<? extends BulletProjectileEntity> pType, Level pLevel) {
            super(pType, pLevel, BulletType.CAL_22);
        }
    }
}
