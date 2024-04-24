package keno.net.rings_of_aeon.effects;

import keno.net.rings_of_aeon.RuinousCall;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RCStatusEffects {
    public static final StatusEffect WEALTHY = registerStatusEffect("wealthy",
            new WealthyEffect(StatusEffectCategory.BENEFICIAL, 0xFFD700)
                    .addAttributeModifier(EntityAttributes.GENERIC_LUCK,
                            "0acf4040-c2c4-4b46-b995-55e8c77b244f",
                            1.0f, EntityAttributeModifier.Operation.ADDITION));

    private static StatusEffect registerStatusEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, RuinousCall.modLoc(name), effect);
    }

    public static void registerEffects() {
        RuinousCall.LOGGER.info("Registering effects for Ruinous Call");
    }
}
