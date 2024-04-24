package keno.net.rings_of_aeon.items;

import keno.net.rings_of_aeon.blocks.AltarOfWealthBlock;
import keno.net.rings_of_aeon.effects.RCStatusEffects;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import keno.net.rings_of_aeon.util.TimeConversion;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TabletOfWealthItem extends Item {
    public TabletOfWealthItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            Vec3d playerCameraPos = user.getCameraPosVec(user.age);
            BlockState playerHighlightedBlock = world.getBlockState(new BlockPos(
                    (int) playerCameraPos.getX(),
                    (int) playerCameraPos.getY(),
                    (int) playerCameraPos.getZ()));
            if (user.getStackInHand(hand).getItem() == this &&
                    (playerHighlightedBlock.getBlock() == RCCommonRegistry.ALTAR_OF_WEALTH
                            && playerHighlightedBlock.get(AltarOfWealthBlock.ITEMS) == 4)) {
                applyEffect(user, hand);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    private void applyEffect(PlayerEntity user, Hand hand) {
        StatusEffect wealthy = RCStatusEffects.WEALTHY;
        if (!user.hasStatusEffect(wealthy)) {
            StatusEffectInstance wealthInstance = new StatusEffectInstance(wealthy, TimeConversion.secondsToTicks(30));
            user.addStatusEffect(wealthInstance);
            user.getItemCooldownManager().set(this, TimeConversion.secondsToTicks(2));
            user.getStackInHand(hand).decrement(1);

        } else if (user.hasStatusEffect(wealthy) && user.getStatusEffect(wealthy).getAmplifier() < 3) {
            StatusEffectInstance previousInstance = user.getStatusEffect(wealthy);
            StatusEffectInstance currentInstance = new StatusEffectInstance(previousInstance.getEffectType(),
                    previousInstance.getDuration() + TimeConversion.secondsToTicks(30), previousInstance.getAmplifier() + 1);
            user.addStatusEffect(currentInstance);
            user.getItemCooldownManager().set(this, TimeConversion.secondsToTicks(1));
            user.getStackInHand(hand).decrement(1);
        }
    }
}
