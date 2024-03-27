package keno.net.rings_of_aeon.items;

import keno.net.rings_of_aeon.RingsOfAeon;
import keno.net.rings_of_aeon.registries.ModCommonRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup RUINOUS_CALL = Registry.register(Registries.ITEM_GROUP,
            new Identifier(RingsOfAeon.MODID, "ruinous_call"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruinous_call"))
                    .icon(() -> new ItemStack(ModCommonRegistry.RELIQUIA_PILLAR))
                    .entries(((displayContext, entries) -> {
                        entries.add(ModCommonRegistry.RELIQUIA_PILLAR);
                        entries.add(ModCommonRegistry.BLOOD_RUSH);
                        entries.add(ModCommonRegistry.DEVIL_FORTUNE);
                        entries.add(ModCommonRegistry.RELIQUIA_BRICK);
                        entries.add(ModCommonRegistry.RELIQUIA_STAIR);
                        entries.add(ModCommonRegistry.RELIQUIA_WALL);
                        entries.add(ModCommonRegistry.RELIQUIA_SLAB);
                        entries.add(ModCommonRegistry.FRAGMEN_POLEARM);
                    })).build());

    public static final RegistryKey<ItemGroup> RUIN_CALL_KEY = getItemGroupKey(RUINOUS_CALL);

    public static void registerItemGroups() {
        RingsOfAeon.LOGGER.info("Registering Item Groups for " + RingsOfAeon.MODID);
    }

    public static RegistryKey<ItemGroup> getItemGroupKey(ItemGroup group) {
        return Registries.ITEM_GROUP.getKey(group).get();
    }
}
