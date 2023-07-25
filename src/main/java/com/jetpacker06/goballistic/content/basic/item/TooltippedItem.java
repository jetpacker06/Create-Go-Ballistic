package com.jetpacker06.goballistic.content.basic.item;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.datagen.GBLang;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TooltippedItem extends Item {
    private final String tooltipKey;

    public TooltippedItem(Properties pProperties, String pTooltipLang, String pTooltipKey) {
        super(pProperties);
        this.tooltipKey = pTooltipKey;
        GBLang.add(pTooltipKey, pTooltipLang);
    }
    public TooltippedItem(Properties properties, String pTooltipLang) {
        this(properties, "tooltip." + GoBallistic.MOD_ID + pTooltipLang.replace(" ", "_")
                .replace(".", "").toLowerCase(), pTooltipLang);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel,
                                @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent(tooltipKey));
    }
}
