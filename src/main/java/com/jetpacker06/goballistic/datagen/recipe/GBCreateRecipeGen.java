package com.jetpacker06.goballistic.datagen.recipe;

import com.jetpacker06.goballistic.GoBallistic;
import com.jetpacker06.goballistic.register.GBFluids;
import com.jetpacker06.goballistic.register.GBItems;
import com.jetpacker06.goballistic.register.GBRecipeTypes;
import com.jetpacker06.goballistic.register.GBTags;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.Create;
import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.data.recipe.MechanicalCraftingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.MechanicalCraftingRecipeGen;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public abstract class GBCreateRecipeGen extends ProcessingRecipeGen {
    public GBCreateRecipeGen(DataGenerator generator) {
        super(generator);
    }

    public <T extends ProcessingRecipe<?>> CreateRecipeProvider.GeneratedRecipe create(String name, UnaryOperator<ProcessingRecipeBuilder<T>> b) {
        return create(new ResourceLocation(GoBallistic.MOD_ID, name), b);
    }

    @Override
    public String getName() {
        return GoBallistic.NAME + "'s Create Recipes";
    }

    public static class Splashing extends GBCreateRecipeGen {
        CreateRecipeProvider.GeneratedRecipe
                saltpeter = create("saltpeter", b -> b
                .require(GBItems.LIMESTONE_CHUNKS.get())
                .output(GBItems.SALTPETER.get()))
                ;
        public Splashing(DataGenerator generator) {
            super(generator);
        }
        @Override
        protected IRecipeTypeInfo getRecipeType() {
            return AllRecipeTypes.SPLASHING;
        }
    }

    public static class Mixing extends GBCreateRecipeGen {
        CreateRecipeProvider.GeneratedRecipe
                gunpowder = create("gunpowder_factory", b -> b
                .require(GBItems.SULFUR.get())
                .require(GBItems.SALTPETER.get())
                .require(Items.CHARCOAL)
                .output(GBFluids.GUNPOWDER.get(), 250))
                ;
        public Mixing(DataGenerator generator) {
            super(generator);
        }
        @Override
        protected IRecipeTypeInfo getRecipeType() {
            return AllRecipeTypes.MIXING;
        }
    }

    public static class Cutting extends GBCreateRecipeGen {
        CreateRecipeProvider.GeneratedRecipe
                minie_ball = create("minie_ball", b -> b
                .require(GBItems.LEAD_INGOT.get())
                .output(GBItems.MINIE_BALL.get(), 2))
                ;
        public Cutting(DataGenerator generator) {
            super(generator);
        }
        @Override
        protected IRecipeTypeInfo getRecipeType() {
            return AllRecipeTypes.CUTTING;
        }
    }

    public static class Filling extends GBCreateRecipeGen {
        CreateRecipeProvider.GeneratedRecipe
                filled_casing_44 = create("filled_casing_44", b -> b
                .require(GBFluids.GUNPOWDER.get(), 350)
                .require(GBItems.CASING_44.get())
                .output(GBItems.FILLED_CASING_44.get())),
                filled_casing_22 = create("filled_casing_22", b -> b
                .require(GBFluids.GUNPOWDER.get(), 250)
                .require(GBItems.CASING_22.get())
                .output(GBItems.FILLED_CASING_22.get())),
                filled_shell_casing = create("filled_shell_casing", b -> b
                .require(GBFluids.GUNPOWDER.get(), 350)
                .require(GBItems.CASING_SHELL.get())
                .output(GBItems.FILLED_CASING_SHELL.get()))
                ;
        public Filling(DataGenerator generator) {
            super(generator);
        }
        @Override
        protected IRecipeTypeInfo getRecipeType() {
            return AllRecipeTypes.FILLING;
        }
    }

    public static class Deploying extends GBCreateRecipeGen {
        CreateRecipeProvider.GeneratedRecipe
                cartridge_44 = create("cartridge_44", b -> b
                .require(GBItems.FILLED_CASING_44.get())
                .require(GBItems.BULLET_44.get())
                .output(GBItems.CARTRIDGE_44.get())),
                cartridge_22 = create("cartridge_22", b -> b
                .require(GBItems.FILLED_CASING_22.get())
                .require(GBItems.BULLET_22.get())
                .output(GBItems.CARTRIDGE_22.get())),
                shell = create("filled_shell_casing", b -> b
                .require(GBItems.FILLED_CASING_SHELL.get())
                .require(GBItems.SHELL_PELLETS.get())
                .output(GBItems.SHELL.get()))
                        ;
        public Deploying(DataGenerator generator) {
            super(generator);
        }
        @Override
        protected IRecipeTypeInfo getRecipeType() {
            return AllRecipeTypes.DEPLOYING;
        }
    }

    public static class MechanicalCrafting extends MechanicalCraftingRecipeGen {
        CreateRecipeProvider.GeneratedRecipe
                cartridge_44 = create(GBItems.CASING_SHELL::get).returns(2)
                .recipe(b -> b.key('P', Items.PAPER)
                        .patternLine("PPP")
                        .patternLine("P P")
                        .patternLine("PPP")
                        .disallowMirrored())
                ;
        public MechanicalCrafting(DataGenerator generator) {
            super(generator);
        }

        GeneratedRecipeBuilder create(Supplier<ItemLike> result) {
            return new GeneratedRecipeBuilder(result);
        }

        public class GeneratedRecipeBuilder {

            private String suffix;
            private final Supplier<ItemLike> result;
            private int amount;

            public GeneratedRecipeBuilder(Supplier<ItemLike> result) {
                this.suffix = "";
                this.result = result;
                this.amount = 1;
            }

            @SuppressWarnings("SameParameterValue")
            GeneratedRecipeBuilder returns(int amount) {
                this.amount = amount;
                return this;
            }

            GeneratedRecipeBuilder withSuffix(String suffix) {
                this.suffix = suffix;
                return this;
            }

            GeneratedRecipe recipe(UnaryOperator<MechanicalCraftingRecipeBuilder> builder) {
                return register(consumer -> {
                    MechanicalCraftingRecipeBuilder b =
                            builder.apply(MechanicalCraftingRecipeBuilder.shapedRecipe(result.get(), amount));
                    ResourceLocation location = Create.asResource("mechanical_crafting/" + RegisteredObjects.getKeyOrThrow(result.get()
                                    .asItem())
                            .getPath() + suffix);
                    b.build(consumer, location);
                });
            }
        }
    }

    public static class Crushing extends GBCreateRecipeGen {
        CreateRecipeProvider.GeneratedRecipe
                crushed_lead = scoriaRecyclingToLead(b -> b.duration(250)
                .output((float) 0.15, AllItems.CRUSHED_LEAD.get(), 1)
                .output((float) 0.15, AllItems.CRUSHED_LEAD.get(), 1)),
                sulfur = create("sulfur", b -> b
                .require(GBTags.Items.BASALT.tag)
                .output(GBItems.SULFUR.get())
                .output(.5f, GBItems.SULFUR.get()))
                ;
        public Crushing(DataGenerator generator) {
            super(generator);
        }
        @Override
        protected IRecipeTypeInfo getRecipeType() {
            return AllRecipeTypes.CRUSHING;
        }

        protected GeneratedRecipe scoriaRecyclingToLead(UnaryOperator<ProcessingRecipeBuilder<ProcessingRecipe<?>>> transform) {
            create(Lang.asId(AllPaletteStoneTypes.SCORIA.name()) + "_recycling", b -> transform.apply(b.require(AllPaletteStoneTypes.SCORIA.materialTag)));
            return create(AllPaletteStoneTypes.SCORIA.getBaseBlock()::get, transform);
        }
        <T extends ProcessingRecipe<?>> GeneratedRecipe create(Supplier<ItemLike> singleIngredient,
                                                               UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
            return create(GoBallistic.MOD_ID, singleIngredient, transform);
        }
    }

    public static class Pressing extends GBCreateRecipeGen {
        CreateRecipeProvider.GeneratedRecipe
                limestone = limestoneRecycle(b -> b.duration(250)
                .output(1f, GBItems.LIMESTONE_CHUNKS.get(), 1)),

                blank_44 = create("blank_44", b -> b
                .require(GBItems.FILLED_CASING_44.get())
                .output(GBItems.BLANK_44.get())),
                blank_22 = create("blank_22", b -> b
                .require(GBItems.FILLED_CASING_22.get())
                .output(GBItems.BLANK_22.get())),
                blank_shell = create("blank_shell", b -> b
                .require(GBItems.FILLED_CASING_SHELL.get())
                .output(GBItems.BLANK_SHELL.get()))
                ;
        public Pressing(DataGenerator generator) {
            super(generator);
        }
        @Override
        protected IRecipeTypeInfo getRecipeType() {
            return AllRecipeTypes.PRESSING;
        }

        protected GeneratedRecipe limestoneRecycle(UnaryOperator<ProcessingRecipeBuilder<ProcessingRecipe<?>>> transform) {
            return create(Lang.asId(AllPaletteStoneTypes.LIMESTONE.name()) + "_stone_type_pressing", b -> transform.apply(b.require(AllPaletteStoneTypes.LIMESTONE.materialTag)));
        }
        <T extends ProcessingRecipe<?>> GeneratedRecipe create(Supplier<ItemLike> singleIngredient,
                                                               UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
            return create(GoBallistic.MOD_ID, singleIngredient, transform);
        }
    }

    public static class Milling extends GBCreateRecipeGen {
        CreateRecipeProvider.GeneratedRecipe
                sulfur = create("sulfur", b -> b
                .require(Items.BASALT)
                .output(GBItems.SULFUR.get()))
                ;
        public Milling(DataGenerator generator) {
            super(generator);
        }
        @Override
        protected IRecipeTypeInfo getRecipeType() {
            return AllRecipeTypes.MILLING;
        }
    }

    public static class Stamping extends GBCreateRecipeGen {
        CreateRecipeProvider.GeneratedRecipe
                stamp_bullet_44 = create("stamp_bullet_44", b -> b
                .require(GBItems.LEAD_INGOT.get())
                .require(GBItems.STAMP_44_CAL_BULLET.get())
                .output(GBItems.BULLET_44.get(), 2)),
                stamp_casing_44 = create("stamp_casing_44", b -> b
                .require(AllItems.BRASS_SHEET.get())
                .require(GBItems.STAMP_44_CAL_BULLET.get())
                .output(GBItems.CASING_44.get(), 2)),
                stamp_bullet_22 = create("stamp_bullet_22", b -> b
                .require(GBItems.LEAD_INGOT.get())
                .require(GBItems.STAMP_22_CAL_BULLET.get())
                .output(GBItems.BULLET_22.get(), 2)),
                stamp_casing_22  = create("stamp_casing_22", b -> b
                .require(AllItems.BRASS_SHEET.get())
                .require(GBItems.STAMP_22_CAL_BULLET.get())
                .output(GBItems.CASING_22.get(), 2))
                ;
        public Stamping(DataGenerator generator) {
            super(generator);
        }
        @Override
        protected IRecipeTypeInfo getRecipeType() {
            return GBRecipeTypes.STAMPING;
        }
    }
}
