package keno.net.rings_of_aeon.items;

import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import keno.net.rings_of_aeon.util.ItemUtils;
import keno.net.rings_of_aeon.util.TimeConversion;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class DevilsFortuneItem extends SwordItem {
    private final String NBT = "nbt.rings_of_aeon.drawn_fortune";

    public DevilsFortuneItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            if (user instanceof ServerPlayerEntity player && player.getStackInHand(hand).getItem() == this) {
                ItemStack stack = ItemUtils.getHeldItem(player, this);
                initializeNBT(player, stack);
                setDrawnFortune(stack, false);
                if (stack.getNbt().getInt(NBT) <= 4) {
                    applyEffectToUser(player, stack);
                } else if (stack.getNbt().getInt(NBT) == 5) {
                    user.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.NEUTRAL, 0.8f, 1f);
                    jackpot(player, stack);
                } else if (stack.getNbt().getInt(NBT) == 6) {
                    user.playSound(SoundEvents.ENTITY_WARDEN_ROAR, SoundCategory.NEUTRAL, 0.8f, 1f);
                    foolsJackpot(player, stack);
                }
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    private void applyEffectToUser(PlayerEntity user, ItemStack stack) {
        if (!user.getWorld().isClient()) {
            List<StatusEffectInstance> statusEffects;
            statusEffects = Stream.of(
                    new StatusEffectInstance(StatusEffects.STRENGTH, TimeConversion.secondsToTicks(15)),
                    new StatusEffectInstance(StatusEffects.RESISTANCE, TimeConversion.secondsToTicks(20)),
                    new StatusEffectInstance(StatusEffects.WEAKNESS, TimeConversion.secondsToTicks(10)),
                    new StatusEffectInstance(StatusEffects.WITHER, TimeConversion.secondsToTicks(5))
            ).toList();
            int data = stack.getNbt().getInt(NBT) - 1;
            user.addStatusEffect(statusEffects.get(data));
            setDrawnFortune(stack, true);
            user.getItemCooldownManager().set(this, TimeConversion.secondsToTicks(30));
        }
    }

    private void jackpot(PlayerEntity user, ItemStack stack) {
        if (!user.getWorld().isClient()) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, TimeConversion.secondsToTicks(10), 2));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, TimeConversion.secondsToTicks(40), 3));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, TimeConversion.secondsToTicks(30)));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, TimeConversion.secondsToTicks(15), 2));
            setDrawnFortune(stack, true);
            user.getItemCooldownManager().set(this, TimeConversion.minutesToTicks(1));
        }
    }

    private void foolsJackpot(PlayerEntity user, ItemStack stack) {
        if (!user.getWorld().isClient()) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, TimeConversion.secondsToTicks(15), 2));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, TimeConversion.secondsToTicks(10)));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, TimeConversion.secondsToTicks(5), 2));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, TimeConversion.secondsToTicks(3)));
            setDrawnFortune(stack, true);
            user.getItemCooldownManager().set(this, TimeConversion.minutesToTicks(1));
        }
    }

    private void setDrawnFortune(ItemStack stack, boolean reset) {
        if (reset) {
            stack.getNbt().putInt(NBT, 0);
            return;
        }
        int randInt = ThreadLocalRandom.current().nextInt(1, 7);
        stack.getNbt().putInt(NBT, randInt);
    }

    private void initializeNBT(PlayerEntity player, ItemStack stack) {
        if (ItemUtils.hasPlayerStackInInv(player, RCCommonRegistry.DEVIL_FORTUNE) && !player.getWorld().isClient()) {
            if (!stack.hasNbt() || stack.getNbt().contains(NBT, NbtElement.INT_TYPE)) {
                stack.getOrCreateNbt().putInt(NBT, 0);
            }
        }
    }
}
