package keno.net.rings_of_aeon.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {

    public VillagerEntityMixin() {
    }

    @ModifyExpressionValue(method = "prepareOffersFor", at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z"))
    public boolean hasCustomEffect(boolean original, @Local(argsOnly = true) PlayerEntity player) {
        return (original || player.hasStatusEffect(StatusEffects.LUCK));
    }

    @ModifyExpressionValue(method = "prepareOffersFor", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerEntity;getStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Lnet/minecraft/entity/effect/StatusEffectInstance;"))
    public StatusEffectInstance changeInstance(StatusEffectInstance original, @Local(argsOnly = true) PlayerEntity player) {
        if (player.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE) && player.hasStatusEffect(StatusEffects.LUCK)) {
            StatusEffectInstance newInstance = player.getStatusEffect(StatusEffects.LUCK);
            if (original.getAmplifier() < newInstance.getAmplifier()) {
                return newInstance;
            }
        } else if (player.hasStatusEffect(StatusEffects.LUCK)) {
            return player.getStatusEffect(StatusEffects.LUCK);
        }
        return original;
    }
}
