package com.jetpacker06.allthecogs.content.block.cogwheel;

import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class Props {
    public static NonNullSupplier<? extends Block> stone = () -> Blocks.ANDESITE;
    public static NonNullSupplier<? extends Block> wood = () -> Blocks.OAK_PLANKS;
    public static NonNullSupplier<? extends Block> metal = () -> Blocks.IRON_BLOCK;
    public static NonNullSupplier<? extends Block> slime = () -> Blocks.SLIME_BLOCK;
    public static NonNullSupplier<? extends Block> dirt = () -> Blocks.DIRT;
    public static NonNullSupplier<? extends Block> hay = () -> Blocks.HAY_BLOCK;
}
