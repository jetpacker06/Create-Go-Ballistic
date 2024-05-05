package com.jetpacker06.goballistic.content.kinetics.stamp;

import com.jetpacker06.goballistic.content.recipe.stamping.StampingRecipe;
import com.jetpacker06.goballistic.register.GBRecipeTypes;
import com.simibubi.create.Create;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipe;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.recipe.RecipeApplier;
import com.simibubi.create.foundation.utility.VecHelper;
import com.simibubi.create.infrastructure.config.AllConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class MechanicalStampBlockEntity extends KineticBlockEntity implements StampingBehavior.StampingBehaviorSpecifics {

    protected ItemStack heldItem = ItemStack.EMPTY;
    public StampingBehavior stampingBehavior;
    private LazyOptional<StampItemHandler> invHandler;

    public MechanicalStampBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public boolean tryProcessOnBelt(TransportedItemStack input, List<ItemStack> outputList, boolean simulate) {
        Optional<StampingRecipe> recipe = getRecipe(input.stack);
        if (recipe.isEmpty())
            return false;
        if (simulate)
            return true;
        stampingBehavior.particleItems.add(input.stack);
        List<ItemStack> outputs = RecipeApplier.applyRecipeOn(
                canProcessInBulk() ? input.stack : ItemHandlerHelper.copyStackWithSize(input.stack, 1), recipe.get());

        outputList.addAll(outputs);
        return true;
    }
    @SuppressWarnings("deprecation")
    @Override
    public boolean tryProcessInWorld(ItemEntity itemEntity, boolean simulate) {
        ItemStack item = itemEntity.getItem();
        Optional<StampingRecipe> recipe = getRecipe(item);
        if (recipe.isEmpty())
            return false;
        if (simulate)
            return true;

        stampingBehavior.particleItems.add(item);
        if (canProcessInBulk() || item.getCount() == 1) {
            RecipeApplier.applyRecipeOn(itemEntity, recipe.get());
        } else {
            for (ItemStack result : RecipeApplier.applyRecipeOn(ItemHandlerHelper.copyStackWithSize(item, 1),
                    recipe.get())) {
                assert level != null;
                ItemEntity created =
                        new ItemEntity(level, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), result);
                created.setDefaultPickUpDelay();
                created.setDeltaMovement(VecHelper.offsetRandomly(Vec3.ZERO, Create.RANDOM, .05f));
                level.addFreshEntity(created);
            }
            item.shrink(1);
        }
        return true;
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        compound.put("HeldItem", heldItem.serializeNBT());
        super.write(compound, clientPacket);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        if (compound.contains("HeldItem"))
            heldItem = ItemStack.of(compound.getCompound("HeldItem"));
        super.read(compound, clientPacket);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (invHandler != null)
            invHandler.invalidate();
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
        if (isItemHandlerCap(cap)) {
            if (invHandler == null)
                initHandler();
            return invHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void initialize() {
        super.initialize();
        initHandler();
    }

    private void initHandler() {
        if (invHandler != null) {
            return;
        }
        invHandler = LazyOptional.of(this::createHandler);
    }

    public StampItemHandler createHandler() {
        return new StampItemHandler(this);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
        stampingBehavior = new StampingBehavior(this);
        behaviours.add(stampingBehavior);
    }

    @Override
    public boolean canProcessInBulk() {
        return AllConfigs.server().recipes.bulkPressing.get();
    }

    @Override
    public int getParticleAmount() {
        return 15;
    }

    @Override
    public float getKineticSpeed() {
        return this.getSpeed();
    }

    public Optional<StampingRecipe> getRecipe(ItemStack item) {
        assert level != null;
        Optional<StampingRecipe> assemblyRecipe =
                SequencedAssemblyRecipe.getRecipe(level, item, GBRecipeTypes.STAMPING.getType(), StampingRecipe.class);
        if (assemblyRecipe.isPresent())
            return assemblyRecipe;
        RecipeWrapper pressingInv = new RecipeWrapper(new ItemStackHandler(2));
        pressingInv.setItem(0, item);
        pressingInv.setItem(1, heldItem);
        return GBRecipeTypes.STAMPING.find(pressingInv, level);
    }

    public StampingBehavior getStampingBehavior() {
        return stampingBehavior;
    }
}
