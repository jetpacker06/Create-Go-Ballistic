package com.jetpacker06.goballistic.content.gun;

import com.jetpacker06.goballistic.content.bullet.AbstractAmmoItem;
import com.jetpacker06.goballistic.content.bullet.BulletSpecific;
import com.jetpacker06.goballistic.content.bullet.BulletType;
import com.jetpacker06.goballistic.content.bullet.projectile.BulletProjectileEntity;
import com.simibubi.create.content.equipment.zapper.ShootableGadgetItemMethods;
import com.simibubi.create.foundation.utility.VecHelper;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public abstract class AbstractGunItem extends ProjectileWeaponItem {
    private final int maxAmmoStorage;
    private final ItemEntry<? extends AbstractAmmoItem> ammoItem;
    private final BulletType bulletType;
    public Predicate<ItemStack> BULLET_MATCHES;

    public AbstractGunItem(Properties properties, BulletType bulletType, int maxAmmoStorage) {
        super(properties);
        this.maxAmmoStorage = maxAmmoStorage;
        this.ammoItem = bulletType.getAmmoItemEntry();
        this.bulletType = bulletType;

        BULLET_MATCHES = (item) -> {
            if (!(item.getItem() instanceof BulletSpecific bullet)) {
                return false;
            }
            return bullet.getBulletType() == this.bulletType;
        };
    }

    public AbstractAmmoItem getAmmoItem() {
        return this.ammoItem.get();
    }

    public int getMaxAmmoStorage() {
        return this.maxAmmoStorage;
    }

    public BulletType getBulletType() {
        return this.bulletType;
    }

    public abstract @NotNull SoundEvent getFireSound();

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return this.BULLET_MATCHES;
    }


    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack pGunStack = pPlayer.getItemInHand(pHand);

        reload(pLevel, pPlayer, pGunStack);

        return super.use(pLevel, pPlayer, pHand);
    }

    public static void reload(Level pLevel, Player pPlayer, ItemStack pGunStack) {
        AbstractGunItem item = (AbstractGunItem) pGunStack.getItem();
        int cartridgesMissing = item.getMaxAmmoStorage() - cartridgesInGun(pGunStack);
        int ammoFound = findAmmo(pPlayer, pGunStack, cartridgesMissing);
        CompoundTag tag = pGunStack.getOrCreateTag();
        tag.putInt("AmmoCount", item.getMaxAmmoStorage() - cartridgesMissing + ammoFound);
    }

    public static int findAmmo(Player pPlayer, ItemStack pGunStack, int cartridgesMissing) {
        ItemStack offhandStack = pPlayer.getItemInHand(InteractionHand.OFF_HAND);
        AbstractGunItem gunItem = (AbstractGunItem) pGunStack.getItem();

        int bulletsFound = 0;
        if (isAmmo(pGunStack, offhandStack)) {
            bulletsFound += offhandStack.split(cartridgesMissing).getCount();
            if (bulletsFound == cartridgesMissing) return bulletsFound;
        }
        for (ItemStack inventoryStack : pPlayer.getInventory().items) {
            if (isAmmo(pGunStack, inventoryStack)) {
                bulletsFound += inventoryStack.split(cartridgesMissing - bulletsFound).getCount();
                if (bulletsFound == cartridgesMissing) return bulletsFound;
            }
        }
        if (pPlayer.isCreative()) {
            return gunItem.getMaxAmmoStorage() - cartridgesMissing;
        }
        return bulletsFound;
    }

    public void onLeftClick(Level pLevel, Player pPlayer, ItemStack pGunStack) {
        if (cartridgesInGun(pGunStack) > 0) {
            fireGun(pLevel, pPlayer, pGunStack);
        }
    }
    
    public static int cartridgesInGun(ItemStack pGunStack) {
        CompoundTag tag = pGunStack.getOrCreateTag();
        if (tag.contains("AmmoCount"))
            return tag.getInt("AmmoCount");
        return 0;        
    }

    public static void decrementAmmo(ItemStack pGunStack) {
        CompoundTag tag = pGunStack.getOrCreateTag();
        tag.putInt("AmmoCount", tag.getInt("AmmoCount") - 1);
    }

    public static void fireGun(Level pLevel, Player pPlayer, ItemStack pGunStack) {
        AbstractGunItem gun = (AbstractGunItem) pGunStack.getItem();
        shootBullet(pLevel, pPlayer, gun);
        decrementAmmo(pGunStack);

        pLevel.playSound(pPlayer, pPlayer.getOnPos().above(), gun.getFireSound(), SoundSource.PLAYERS, 1f, 1f);
    }

    public static void shootBullet(Level pLevel, Player pPlayer, AbstractGunItem item) {
        BulletType type = item.getBulletType();

        Vec3 barrelPos = ShootableGadgetItemMethods.getGunBarrelVec(pPlayer, true,
                new Vec3(.75f, -0.15f, 1.5f));
        Vec3 correction =
                ShootableGadgetItemMethods.getGunBarrelVec(pPlayer, true, new Vec3(-.05f, 0, 0))
                        .subtract(pPlayer.position()
                                .add(0, pPlayer.getEyeHeight(), 0));

        Vec3 motion = pPlayer.getLookAngle().add(correction)
                .normalize()
                .scale(2);

        Vec3 sprayBase = VecHelper.rotate(new Vec3(0, 0.1, 0), 360 * pPlayer.getRandom().nextFloat(), Direction.Axis.Z);
        for (int i = 0; i < type.getSpread(); i++) {
            item.fireProjectile(pPlayer, pLevel, motion, barrelPos, sprayBase, i);
        }
    }

    public void fireProjectile(Player pPlayer, Level pLevel, Vec3 motion, Vec3 barrelPos, Vec3 sprayBase, int iteration) {
        BulletProjectileEntity projectile = this.bulletType.getEntity().create(pLevel);
        float sprayChange = 360f / this.bulletType.getSpread();
        if (this.bulletType.getSpread() > 1) {
            float imperfection = 25 * (pPlayer.getRandom().nextFloat() - 0.5f);
            Vec3 sprayOffset = VecHelper.rotate(sprayBase, iteration * sprayChange + imperfection, Direction.Axis.Z);
            motion = motion.add(VecHelper.lookAt(sprayOffset, motion));
        }

        assert projectile != null;
        projectile.setPos(barrelPos.x, barrelPos.y, barrelPos.z);
        projectile.setDeltaMovement(motion);
        projectile.setOwner(pPlayer);
        pLevel.addFreshEntity(projectile);
    }

    public static BulletType getRequiredBulletType(ItemStack pGunStack) {
        if (!(pGunStack.getItem() instanceof AbstractGunItem gunItem)) throw new IllegalArgumentException("ItemStack must contain a gun item.");
        return gunItem.getBulletType();
    }

    public static boolean isAmmo(ItemStack pGunStack, ItemStack ammoCandidate) {
        if (!(ammoCandidate.getItem() instanceof AbstractAmmoItem bullet)) return false;
        return bullet.getBulletType() == getRequiredBulletType(pGunStack);
    }

}
