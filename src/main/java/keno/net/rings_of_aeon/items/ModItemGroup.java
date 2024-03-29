package keno.net.rings_of_aeon.items;

import keno.net.rings_of_aeon.RingsOfAeon;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
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
                    .icon(() -> new ItemStack(RCCommonRegistry.RELIQUIA_PILLAR))
                    .entries(((displayContext, entries) -> {
                        entries.add(RCCommonRegistry.RELIQUIA_PILLAR);
                        entries.add(RCCommonRegistry.BLOOD_RUSH);
                        entries.add(RCCommonRegistry.DEVIL_FORTUNE);
                        entries.add(RCCommonRegistry.RELIQUIA_BRICK);
                        entries.add(RCCommonRegistry.RELIQUIA_STAIR);
                        entries.add(RCCommonRegistry.RELIQUIA_WALL);
                        entries.add(RCCommonRegistry.RELIQUIA_SLAB);
                        entries.add(RCCommonRegistry.FRAGMEN_POLEARM);
                        entries.add(RCCommonRegistry.FRAGMEN_POTTERY_SHERD);
                        entries.add(RCCommonRegistry.CLOUD_OWL_POTTERY_SHERD);
                    })).build());

    public static final RegistryKey<ItemGroup> RUIN_CALL_KEY = getItemGroupKey(RUINOUS_CALL);

    public static void registerItemGroups() {
        RingsOfAeon.LOGGER.info("Registering Item Groups for " + RingsOfAeon.MODID);
    }

    public static RegistryKey<ItemGroup> getItemGroupKey(ItemGroup group) {
        return Registries.ITEM_GROUP.getKey(group).get();
    }
}
