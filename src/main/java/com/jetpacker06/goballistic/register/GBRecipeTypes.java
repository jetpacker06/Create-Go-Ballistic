package com.jetpacker06.goballistic.register;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.content.kinetics.stamp.StampingRecipe;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public enum GBRecipeTypes implements IRecipeTypeInfo {

    STAMPING(StampingRecipe::new);

    private final ResourceLocation id;
    private final RegistryObject<RecipeSerializer<?>> serializerObject;
    @Nullable
    @SuppressWarnings("all")
    private final RegistryObject<RecipeType<?>> typeObject;
    @SuppressWarnings("all")
    private final Supplier<RecipeType<?>> type;

    GBRecipeTypes(ProcessingRecipeBuilder.ProcessingRecipeFactory<?> processingFactory) {
        this(() -> new ProcessingRecipeSerializer<>(processingFactory));
    }
    GBRecipeTypes(Supplier<RecipeSerializer<?>> serializerSupplier) {
        String name = Lang.asId(this.name());
        id = GoBallistic.resource(name);
        serializerObject = Registers.SERIALIZER_REGISTER.register(name, serializerSupplier);
        typeObject = Registers.TYPE_REGISTER.register(name, () -> AllRecipeTypes.simpleType(id));
        type = typeObject;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends RecipeSerializer<?>> T getSerializer() {
        return (T) serializerObject.get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends RecipeType<?>> T getType() {
        return (T) type.get();
    }

    public static class Registers {
        private static final DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GoBallistic.MOD_ID);
        private static final DeferredRegister<RecipeType<?>> TYPE_REGISTER = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, GoBallistic.MOD_ID);

        public static void register(IEventBus eventBus) {
            SERIALIZER_REGISTER.register(eventBus);
            TYPE_REGISTER.register(eventBus);

            // Necessary to ensure it loads
            @SuppressWarnings("unused")
            GBRecipeTypes r = GBRecipeTypes.STAMPING;
        }
    }
    public <C extends Container, T extends Recipe<C>> Optional<T> find(C inv, Level world) {
        return world.getRecipeManager()
                .getRecipeFor(getType(), inv, world);
    }
}
