package keno.net.rings_of_aeon.items;

import keno.net.rings_of_aeon.RuinousCall;
import keno.net.rings_of_aeon.util.MathUtils;
import keno.net.rings_of_aeon.util.TimeConversion;
import keno.net.rings_of_aeon.util.TimerAccess;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FragmenPolearmItem extends SwordItem implements TimerAccess {
    //TODO fix penalty player damage (Post-release, I'm not having this stop development)
    private final String POLEARM_DAMAGE = "nbt." + RuinousCall.MODID + ".polearm_damage";
    private final String COMBO = "nbt." + RuinousCall.MODID + "polearm_combo";
    private int ticks;

    public FragmenPolearmItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        initializeNBT(stack);
        if (entity instanceof PlayerEntity player && !player.getWorld().isClient()) {
            if (player.getMainHandStack().getItem() == this) {
                spearSpeedPenalty(player, stack);
                spearTick(player, stack);
            } else {
                setSpearDamage(stack, 1.5f);
                stack.getNbt().putInt(COMBO, 0);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            user.removeStatusEffect(StatusEffects.SPEED);
            user.removeStatusEffect(StatusEffects.HASTE);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, TimeConversion.secondsToTicks(5)));
            user.getItemCooldownManager().set(this, TimeConversion.secondsToTicks(10));
        }
        return super.use(world, user, hand);
    }

    private void spearSpeedPenalty(PlayerEntity player, ItemStack stack) {
        if (player.getMovementSpeed() >= 0.2f && !player.getWorld().isClient()) {
            // DamageSource source = ModDamageTypes.of(player.getWorld(), ModDamageTypes.TOO_FAST);\
            // player.damage(source, 1.0f);
            // For some reason, the damage won't apply to the player, this gotta be gradle black magic or smth >:(
            stack.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        }
    }

    public void spearTick(PlayerEntity player, ItemStack stack) {
        if (--this.ticks == 0) {
            setSpearDamage(stack, 1.5f);
        } else {
            spearDamageScale(stack, player);
        }
    }

    private void spearDamageScale(ItemStack stack, PlayerEntity player) {
        if (--this.ticks == 0) {
            setSpearDamage(stack, 1.5f);
            stack.getNbt().putInt(COMBO, 0);
        } else {
            if (stack.getNbt().getInt(COMBO) >= 3) {
                setSpearDamage(stack, getSpearDamage(stack) + 2);
                applyPotionEffect(player);
                stack.getNbt().putInt(COMBO, 0);
            }
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ringsOfAeonSetTimer(TimeConversion.secondsToTicks(5));
        stack.getNbt().putInt(COMBO, stack.getNbt().getInt(COMBO) + 1);
        if (attacker instanceof PlayerEntity player && !player.getWorld().isClient()) {
            target.damage(target.getDamageSources().playerAttack(player), getSpearDamage(stack));
        }
        return super.postHit(stack, target, attacker);
    }

    private void initializeNBT(ItemStack stack) {
        if (!stack.hasNbt()) {
            stack.getOrCreateNbt().putFloat(POLEARM_DAMAGE, 1.5f);
            stack.getOrCreateNbt().putInt(COMBO, 0);
        }
    }

    public void setSpearDamage(ItemStack stack, float damage) {
        stack.getNbt().putFloat(POLEARM_DAMAGE, MathUtils.clamp(1.5f, damage, 30.0f));
    }

    public float getSpearDamage(ItemStack stack) {
        return stack.getNbt().getFloat(POLEARM_DAMAGE);
    }

    private void applyPotionEffect(PlayerEntity player) {
        StatusEffect effect = StatusEffects.SPEED;
        if (!player.hasStatusEffect(effect)) {
            player.addStatusEffect(new StatusEffectInstance(effect, TimeConversion.secondsToTicks(15), 1));
        } else {
            player.addStatusEffect(new StatusEffectInstance(effect, TimeConversion.secondsToTicks(15), 2));
            player.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.HASTE, TimeConversion.secondsToTicks(10))));
        }
    }

    @Override
    public void ringsOfAeonSetTimer(int ticks) {
        this.ticks = ticks;
    }
}
