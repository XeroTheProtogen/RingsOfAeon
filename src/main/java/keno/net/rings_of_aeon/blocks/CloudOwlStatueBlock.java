package keno.net.rings_of_aeon.blocks;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class CloudOwlStatueBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public CloudOwlStatueBlock(Settings settings) {
        super(settings);
        settings.dropsLike(Blocks.OAK_PLANKS);
        settings.nonOpaque();
    }

    private static VoxelShape NORTH_FACING = VoxelShapes.combineAndSimplify(Block.createCuboidShape(4, 11, 5, 12, 19, 11),
            Block.createCuboidShape(4, 0, 5, 12, 11, 11), BooleanBiFunction.OR);
    private static VoxelShape EAST_FACING = VoxelShapes.combineAndSimplify(Block.createCuboidShape(5, 11, 4, 11, 19, 12),
            Block.createCuboidShape(5, 0, 4, 11, 11, 12), BooleanBiFunction.OR);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH, SOUTH -> NORTH_FACING;
            case WEST, EAST -> EAST_FACING;
            default -> super.getOutlineShape(state, world, pos, context);
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
