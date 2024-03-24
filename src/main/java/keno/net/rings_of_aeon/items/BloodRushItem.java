package keno.net.rings_of_aeon.items;

import keno.net.rings_of_aeon.registries.ModCommonRegistry;
import keno.net.rings_of_aeon.util.ItemUtils;
import keno.net.rings_of_aeon.util.MathUtils;
import keno.net.rings_of_aeon.util.TimeConversion;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BloodRushItem extends SwordItem {
    // NBT Key for health storage
    private final String LIFE_ESSENCE = "nbt.rings_of_aeon.life_essence";

    public BloodRushItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            if (user instanceof ServerPlayerEntity player && player.getMainHandStack().getItem() == this) {
                ItemStack stack = ItemUtils.getHeldItem(user, this);
                if (stack.getNbt().getFloat(LIFE_ESSENCE) <= 0f) setStoredHealth(player, stack, false);
                else {
                    player.setHealth(player.getHealth() + (getStoredHealth(stack) / 2));
                    setStoredHealth(user, stack, true);
                    player.getItemCooldownManager().set(this, TimeConversion.secondsToTicks(5));
                }
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player && !player.getWorld().isClient()) {
            if (getStoredHealth(stack) > 0) bloodRushHit(player, target);
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.getNbt().getFloat(LIFE_ESSENCE) > 0;
    }

    private void bloodRushHit(PlayerEntity player, LivingEntity target) {
        if (player.getMainHandStack().getItem() == ModCommonRegistry.BLOOD_RUSH) {
            ItemStack stack = ItemUtils.getHeldItem(player, this);
            if (getStoredHealth(stack) > 0) {
                player.setHealth(player.getHealth() + getStoredHealth(stack));
                player.playSound(SoundEvents.ITEM_TOTEM_USE, SoundCategory.NEUTRAL, 0.8f, 1f);
                setStoredHealth(player, stack, true);
                DamageSource source = target.getDamageSources().playerAttack(player);
                target.damage(source, (getAttackDamage() * 2) * damageScalar(target));
                player.getItemCooldownManager().set(this, TimeConversion.secondsToTicks(10));
            }
        }
    }

    private float damageScalar(LivingEntity target) {
        if (target instanceof PlayerEntity) {
            return 1.5f;
        }
        final float health = target.getMaxHealth();
        if (MathUtils.isBetween(1, 50, health)) return 2f;
        if (MathUtils.isBetween(51, 100, health)) return 3f;
        if (MathUtils.isBetween(100, 200, health)) return 4f;
        if (MathUtils.isBetween(200, Float.POSITIVE_INFINITY, health)) return 6f;
        return 1f;
    }

    private void setStoredHealth(PlayerEntity player, ItemStack stack, boolean resetCount) {
        if (ItemUtils.hasPlayerStackInInv(player, ModCommonRegistry.BLOOD_RUSH) && !player.getWorld().isClient) {
            if (!stack.getNbt().contains(LIFE_ESSENCE, NbtElement.FLOAT_TYPE) || !stack.hasNbt()) {
                stack.getOrCreateNbt().putFloat(LIFE_ESSENCE, 0);
            }
            NbtCompound data = stack.getNbt();
            if (resetCount) {
                data.putFloat(LIFE_ESSENCE, 0);
                return;
            }
            data.putFloat(LIFE_ESSENCE, player.getHealth() - 1);
            player.setHealth(1f);
        }
    }

    private float getStoredHealth(ItemStack stack) {
        return stack.getNbt().getFloat(LIFE_ESSENCE);
    }
}
