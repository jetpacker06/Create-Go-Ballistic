package com.jetpacker06.allthecogs.jei;

import com.jetpacker06.allthecogs.AllTheCogs;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
@SuppressWarnings("unused")
public class ATCJEIPlugin implements IModPlugin {

    public static ResourceLocation UID = new ResourceLocation(AllTheCogs.MOD_ID, "jei_plugin");

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration reg) {
        reg.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, List.of(
        ));

        reg.getIngredientManager().removeIngredientsAtRuntime(ForgeTypes.FLUID_STACK, List.of(
        ));
    }
}
