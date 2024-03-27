package keno.net.rings_of_aeon.registries;

import keno.net.rings_of_aeon.RingsOfAeon;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> TOO_FAST = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(RingsOfAeon.MODID, "too_fast"));
    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
