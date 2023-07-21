package com.jetpacker06.goballistic.content.kinetics.stamp;

import com.jetpacker06.goballistic.register.GBBlockEntities;
import com.jetpacker06.goballistic.register.GBBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllShapes;
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.placement.IPlacementHelper;
import com.simibubi.create.foundation.placement.PlacementHelpers;
import com.simibubi.create.foundation.placement.PlacementOffset;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Predicate;

public class MechanicalStampBlock extends HorizontalKineticBlock implements IBE<MechanicalStampBlockEntity> {
    public MechanicalStampBlock(Properties properties) {
        super(properties);
    }
    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext
                && ((EntityCollisionContext) context).getEntity() instanceof Player)
            return AllShapes.CASING_14PX.get(Direction.DOWN);

        return AllShapes.MECHANICAL_PROCESSOR_SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction preferredSide = getPreferredHorizontalFacing(context);
        if (preferredSide != null)
            return defaultBlockState().setValue(HORIZONTAL_FACING, preferredSide);
        return super.getStateForPlacement(context);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(HORIZONTAL_FACING).getAxis();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() == state.getValue(HORIZONTAL_FACING)
                .getAxis();
    }

    @Override
    public Class<MechanicalStampBlockEntity> getBlockEntityClass() {
        return MechanicalStampBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends MechanicalStampBlockEntity> getBlockEntityType() {
        return GBBlockEntities.MECHANICAL_STAMP.get();
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public @NotNull InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn,
                                          BlockHitResult hit) {
        ItemStack heldByPlayer = player.getItemInHand(handIn)
                .copy();

        IPlacementHelper placementHelper = PlacementHelpers.get(placementHelperId);
        if (!player.isShiftKeyDown() && player.mayBuild()) {
            if (placementHelper.matchesItem(heldByPlayer) && placementHelper.getOffset(player, worldIn, state, pos, hit)
                    .placeInWorld(worldIn, (BlockItem) heldByPlayer.getItem(), player, handIn, hit)
                    .consumesAction())
                return InteractionResult.SUCCESS;
        }

        if (AllItems.WRENCH.isIn(heldByPlayer))
            return InteractionResult.PASS;

        if (hit.getDirection() != Direction.DOWN)
            return super.use(state, worldIn, pos, player, handIn, hit);

        withBlockEntityDo(worldIn, pos, be -> {
            player.setItemInHand(handIn, be.heldItem);
            be.heldItem = heldByPlayer;
            be.sendData();
        });

        return InteractionResult.SUCCESS;
    }

    private static final int placementHelperId = PlacementHelpers.register(new IPlacementHelper() {

        @Override
        public @NotNull Predicate<ItemStack> getItemPredicate() {
            return GBBlocks.MECHANICAL_STAMP::isIn;
        }

        @Override
        public @NotNull Predicate<BlockState> getStatePredicate() {
            return GBBlocks.MECHANICAL_STAMP::has;
        }

        @Override
        public @NotNull PlacementOffset getOffset(Player player, Level world, BlockState state, BlockPos pos,
                                                  BlockHitResult ray) {
            List<Direction> directions = IPlacementHelper.orderedByDistanceExceptAxis(pos, ray.getLocation(),
                    state.getValue(HORIZONTAL_FACING)
                            .getAxis(),
                    dir -> world.getBlockState(pos.relative(dir))
                            .getMaterial()
                            .isReplaceable());

            if (directions.isEmpty())
                return PlacementOffset.fail();
            else {
                return PlacementOffset.success(pos.relative(directions.get(0)),
                        s -> s.setValue(HORIZONTAL_FACING, state.getValue(HORIZONTAL_FACING)));
            }
        }
    });
}
