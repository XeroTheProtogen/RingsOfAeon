package keno.net.rings_of_aeon.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import keno.net.rings_of_aeon.effects.RCStatusEffects;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {

    @Unique
    final StatusEffect wealthy = RCStatusEffects.WEALTHY;

    public VillagerEntityMixin() {
    }

    @ModifyExpressionValue(method = "prepareOffersFor", at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z"))
    public boolean hasCustomEffect(boolean original, @Local(argsOnly = true) PlayerEntity player) {
        return (original || player.hasStatusEffect(wealthy));
    }

    @ModifyExpressionValue(method = "prepareOffersFor", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerEntity;getStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Lnet/minecraft/entity/effect/StatusEffectInstance;"))
    public StatusEffectInstance changeInstance(StatusEffectInstance original, @Local(argsOnly = true) PlayerEntity player) {
        if (player.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE) && player.hasStatusEffect(wealthy)) {
            StatusEffectInstance newInstance = player.getStatusEffect(wealthy);
            if (original.getAmplifier() < newInstance.getAmplifier()) {
                return newInstance;
            }
        } else if (player.hasStatusEffect(wealthy)) {
            return player.getStatusEffect(wealthy);
        }
        return original;
    }
}
