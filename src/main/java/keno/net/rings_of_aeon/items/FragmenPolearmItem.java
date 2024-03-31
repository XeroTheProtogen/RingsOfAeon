package keno.net.rings_of_aeon.items;

import keno.net.rings_of_aeon.RuinousCall;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import keno.net.rings_of_aeon.util.ItemUtils;
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
import net.minecraft.item.Items;
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
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.IRON_INGOT);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        initializeNBT(stack);
        if (entity instanceof PlayerEntity player && !player.getWorld().isClient()
                && ItemUtils.hasPlayerStackInInv(player, RCCommonRegistry.FRAGMEN_POLEARM)) {
            if (player.getMainHandStack() == stack) {
                spearSpeedPenalty(player, stack);
            }
            if (--this.ticks == 0) {
                stack.getNbt().putInt(COMBO, 0);
                setSpearDamage(stack, 1f);
            } else {
                spearDamageScale(stack, player);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient() && user.getMainHandStack().getNbt().getInt(COMBO) > 1) {
            user.removeStatusEffect(StatusEffects.SPEED);
            user.removeStatusEffect(StatusEffects.HASTE);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, TimeConversion.secondsToTicks(5)));
            setSpearDamage(user.getMainHandStack(), 1f);
            user.getMainHandStack().getNbt().putInt(COMBO, 0);
            user.getItemCooldownManager().set(this, TimeConversion.secondsToTicks(10));
        }
        return super.use(world, user, hand);
    }

    private void spearSpeedPenalty(PlayerEntity player, ItemStack stack) {
        if (!player.getWorld().isClient() && checkPlayerEffects(player, StatusEffects.SPEED)) {
            // For some reason, the damage type wouldn't apply to the player, so I had to scrap it :(
            stack.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        }
    }

    private boolean checkPlayerEffects(PlayerEntity player, StatusEffect effectType) {
        return player.getStatusEffect(effectType) != null && player.getStatusEffect(effectType).getAmplifier() >= 3;
    }

    private void spearDamageScale(ItemStack stack, PlayerEntity player) {
        if (stack.getNbt().getInt(COMBO) >= 2){
            setSpearDamage(stack, stack.getNbt().getInt(COMBO));
            if (this.ticks == 99) {
                switch (stack.getNbt().getInt(COMBO)) {
                    case 5:
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,
                                TimeConversion.secondsToTicks(15), 1));
                    case 10: {
                        player.removeStatusEffect(StatusEffects.SPEED);
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, TimeConversion.secondsToTicks(15), 2));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, TimeConversion.secondsToTicks(15)));
                        break;
                    }
                    case 15, 20, 25, 30: {
                        player.removeStatusEffect(StatusEffects.SPEED);
                        player.removeStatusEffect(StatusEffects.HASTE);
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, TimeConversion.secondsToTicks(10), 3));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, TimeConversion.secondsToTicks(10), 2));
                    }
                }
            }
        }
    }

    //Damage detection
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
            stack.getOrCreateNbt().putFloat(POLEARM_DAMAGE, 1f);
            stack.getOrCreateNbt().putInt(COMBO, 0);
        }
    }

    //Damage getter & setter
    public void setSpearDamage(ItemStack stack, float damage) {
        stack.getNbt().putFloat(POLEARM_DAMAGE, MathUtils.clamp(0f, damage, 30.0f));
    }

    public float getSpearDamage(ItemStack stack) {
        return stack.getNbt().getFloat(POLEARM_DAMAGE);
    }

    //Timer
    @Override
    public void ringsOfAeonSetTimer(int ticks) {
        this.ticks = ticks;
    }
}
