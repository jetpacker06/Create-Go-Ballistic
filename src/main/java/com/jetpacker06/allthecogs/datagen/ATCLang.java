package com.jetpacker06.allthecogs.datagen;

import com.jetpacker06.allthecogs.AllTheCogs;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.FluidEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.jetbrains.annotations.NotNull;

public class ATCLang extends LanguageProvider {
    public ATCLang(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add("itemGroup.atc", AllTheCogs.NAME);
    }

    public <T extends Block> void add(@NotNull BlockEntry<T> entry, @NotNull String name) {
        this.add(entry.get(), name);
    }

    public <T extends Item> void add(@NotNull ItemEntry<T> entry, @NotNull String name) {
        this.add(entry.get(), name);
    }

    public <T extends ForgeFlowingFluid.Flowing> void add(@NotNull FluidEntry<T> entry, @NotNull String name) {
        this.add("block." + entry.getId().toString().replace(":flowing_", "."), name);
    }

    public void add(@NotNull ForgeFlowingFluid.Flowing fluid, @NotNull String name) {
        assert fluid.getRegistryName() != null;
        String fluidName = fluid.getRegistryName().toString();
        this.add("fluid." + fluidName.replace(":", ".").replace("flowing_", ""), name);
    }
}