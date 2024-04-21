package keno.net.rings_of_aeon.items;

import keno.net.rings_of_aeon.effects.RCStatusEffects;
import keno.net.rings_of_aeon.util.TimeConversion;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TabletOfWealthItem extends Item {
    public TabletOfWealthItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            if (user.getMainHandStack().getItem() == this) {
                applyEffect(user);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    private void applyEffect(PlayerEntity user) {
        StatusEffect wealthy = RCStatusEffects.WEALTHY;
        if (!user.hasStatusEffect(wealthy)) {
            StatusEffectInstance wealthInstance = new StatusEffectInstance(wealthy, TimeConversion.secondsToTicks(30));
            user.addStatusEffect(wealthInstance);
            user.getItemCooldownManager().set(this, TimeConversion.secondsToTicks(2));
        } else if (user.hasStatusEffect(wealthy) && user.getStatusEffect(wealthy).getAmplifier() < 3) {
            StatusEffectInstance previousInstance = user.getStatusEffect(wealthy);
            StatusEffectInstance currentInstance = new StatusEffectInstance(previousInstance.getEffectType(),
                    previousInstance.getDuration() + TimeConversion.secondsToTicks(30), previousInstance.getAmplifier() + 1);
            user.addStatusEffect(currentInstance);
            user.getItemCooldownManager().set(this, TimeConversion.secondsToTicks(1));
        }
        if (user.hasStatusEffect(wealthy) && user.getStatusEffect(wealthy).getAmplifier() < 3) {
            user.getMainHandStack().decrement(1);
        }
    }
}
