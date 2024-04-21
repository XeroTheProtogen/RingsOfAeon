package keno.net.rings_of_aeon.blocks.entities;

import keno.net.rings_of_aeon.RuinousCall;
import keno.net.rings_of_aeon.registries.RCTileEntities;
import keno.net.rings_of_aeon.effects.RCStatusEffects;
import keno.net.rings_of_aeon.util.ImplementedInventory;
import keno.net.rings_of_aeon.util.TimeConversion;
import keno.net.rings_of_aeon.util.TimerAccess;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AltarOfWealthTileEntity extends BlockEntity implements ImplementedInventory, TimerAccess {
    public DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private int ticks;
    public AltarOfWealthTileEntity(BlockPos pos, BlockState state) {
        super(RCTileEntities.ALTAR_OF_WEALTH, pos, state);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    protected void writeNbt(NbtCompound tag) {
        Inventories.writeNbt(tag, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    public boolean invHasItems() {
        return this.inventory.get(0) != ItemStack.EMPTY;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (!world.isClient()) {
            if (invHasItems()) {
                RuinousCall.LOGGER.info("true");
                if (this.ticks <= 0) {
                    ringsOfAeonSetTimer(TimeConversion.minutesToTicks(5));
                    this.inventory.get(0).decrement(1);
                }
            }
            if (this.ticks-- > 0) {
                RuinousCall.LOGGER.info(this.ticks + "");
                List<? extends PlayerEntity> players = world.getPlayers();
                for (PlayerEntity player : players) {
                    boolean isNear = player.getBlockPos().isWithinDistance(pos, 32.0d);
                    boolean isGoingAway = player.getBlockPos().isWithinDistance(pos, 48.0D) && !isNear;
                    if (isNear) {
                        player.addStatusEffect(new StatusEffectInstance(RCStatusEffects.WEALTHY, TimeConversion.minutesToTicks(5)));
                    }
                    if (player.hasStatusEffect(RCStatusEffects.WEALTHY) && isGoingAway) {
                        player.removeStatusEffect(RCStatusEffects.WEALTHY);
                    }
                }
            }
        }
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound compound = createNbt();
        readNbt(compound);

        return compound;
    }

    @Override
    public void ringsOfAeonSetTimer(int ticks) {
        this.ticks = ticks;
    }
}
