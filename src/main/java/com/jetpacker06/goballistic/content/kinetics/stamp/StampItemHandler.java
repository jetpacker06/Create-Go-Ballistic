package com.jetpacker06.goballistic.content.kinetics.stamp;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

public class StampItemHandler implements IItemHandlerModifiable {
    public MechanicalStampBlockEntity be;
    public ItemStack stack = ItemStack.EMPTY;
    public StampItemHandler(MechanicalStampBlockEntity be) {
        this.be = be;
    }

    @Override
    public void setStackInSlot(int slot, @NotNull ItemStack stack) {

    }

    @Override
    public int getSlots() {
        return 1;
    }

    @NotNull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return stack;
    }

    @NotNull
    @Override
    public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (stack.isEmpty()) {
            if (!simulate)
                setStampStack(stack);
            return ItemStack.EMPTY;
        }
        if (!ItemHandlerHelper.canItemStacksStack(this.stack, stack))
            return stack;

        int space = this.stack.getMaxStackSize() - this.stack.getCount();
        ItemStack remainder = stack.copy();
        ItemStack split = remainder.split(space);

        if (space == 0)
            return stack;
        if (!simulate) {
            this.stack = this.stack.copy();
            this.stack.setCount(this.stack.getCount() + split.getCount());
            setStampStack(this.stack);
        }

        return remainder;
    }

    @NotNull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (amount == 0 || stack.isEmpty())
            return ItemStack.EMPTY;
        if (simulate)
            return stack.copy()
                    .split(amount);
        ItemStack toReturn = stack.split(amount);
        be.setChanged();
        be.sendData();
        return toReturn;
    }

    @Override
    public int getSlotLimit(int slot) {
        return Math.min(getStackInSlot(slot).getMaxStackSize(), 64);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return true;
    }

    @SuppressWarnings("all")
    public void setStampStack(ItemStack stack) {
        if (be.getLevel().isClientSide)
            return;
        this.stack = stack;
        this.be.setChanged();
        this.be.sendData();
    }
}
