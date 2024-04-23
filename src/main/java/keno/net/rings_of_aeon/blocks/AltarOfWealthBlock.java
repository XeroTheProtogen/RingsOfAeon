package keno.net.rings_of_aeon.blocks;

import com.mojang.serialization.MapCodec;
import keno.net.rings_of_aeon.blocks.entities.AltarOfWealthTileEntity;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import keno.net.rings_of_aeon.registries.RCTileEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AltarOfWealthBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty ITEMS = IntProperty.of("items", 0, 4);

    public AltarOfWealthBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(ITEMS);
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof AltarOfWealthTileEntity) {
                ItemScatterer.spawn(world, pos, (AltarOfWealthTileEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AltarOfWealthTileEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity tile = world.getBlockEntity(pos);
        if (tile instanceof AltarOfWealthTileEntity altar) {
            ItemStack tabletsInAltar = altar.inventory.get(0);
            if (tabletsInAltar.getCount() < 4) {
                ItemStack heldItem = player.getStackInHand(hand);
                if (!heldItem.isEmpty() && heldItem.getItem() == RCCommonRegistry.TABLET_OF_WEALTH) {
                    setAltarToPlayerItem(player.getStackInHand(hand).copy(), altar.inventory);
                    player.getStackInHand(hand).decrement(1);
                    world.setBlockState(pos, this.setItems(altar.inventory.get(0).getCount()), Block.NOTIFY_LISTENERS);
                }
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, RCTileEntities.ALTAR_OF_WEALTH,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    private void setAltarToPlayerItem(ItemStack stack, DefaultedList<ItemStack> altarInventory) {
        if (altarInventory.get(0).isEmpty()) {
            stack.setCount(1);
            altarInventory.set(0, stack);
        } else {
            altarInventory.get(0).increment(1);
        }
    }

    public IntProperty getItemsProperty() {
        return ITEMS;
    }

    public BlockState setItems(int items) {
        return this.getDefaultState().with(this.getItemsProperty(), items);
    }
}
