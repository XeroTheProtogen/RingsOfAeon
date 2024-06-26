package keno.net.rings_of_aeon.items;

import keno.net.rings_of_aeon.RuinousCall;
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
            new Identifier(RuinousCall.MODID, "ruinous_call"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruinous_call"))
                    .icon(() -> new ItemStack(RCCommonRegistry.RELIQUIA_PILLAR))
                    .entries(((displayContext, entries) -> {
                        addBlocks(entries);
                        addItems(entries);
                    })).build());

    public static final RegistryKey<ItemGroup> RUIN_CALL_KEY = getItemGroupKey(RUINOUS_CALL);

    public static void registerItemGroups() {
        RuinousCall.LOGGER.info("Registering Item Groups for " + RuinousCall.MODID);
    }

    public static RegistryKey<ItemGroup> getItemGroupKey(ItemGroup group) {
        return Registries.ITEM_GROUP.getKey(group).get();
    }

    private static void addBlocks(ItemGroup.Entries entries) {
        entries.add(RCCommonRegistry.ARCHIVIST_TABLE);
        entries.add(RCCommonRegistry.UNKNOWN_CAT_SKULL);
        entries.add(RCCommonRegistry.CLOUD_OWL_STATUE);
        entries.add(RCCommonRegistry.RELIQUIA_BRICKS);
        entries.add(RCCommonRegistry.RELIQUIA_STAIRS);
        entries.add(RCCommonRegistry.RELIQUIA_SLAB);
        entries.add(RCCommonRegistry.RELIQUIA_WALL);
        entries.add(RCCommonRegistry.RELIQUIA_PILLAR);
        entries.add(RCCommonRegistry.ALTAR_OF_WEALTH);
    }

    private static void addItems(ItemGroup.Entries entries) {
        entries.add(RCCommonRegistry.FRAGMEN_ARCHIVE_VOL_ONE);
        entries.add(RCCommonRegistry.TABLET_OF_WEALTH);
        entries.add(RCCommonRegistry.HUMAN_POTTERY_SHERD);
        entries.add(RCCommonRegistry.UNKNOWN_CAT_POTTERY_SHERD);
        entries.add(RCCommonRegistry.CLOUD_OWL_POTTERY_SHERD);
        entries.add(RCCommonRegistry.FRAGMEN_POTTERY_SHERD);
        entries.add(RCCommonRegistry.BLOOD_RUSH);
        entries.add(RCCommonRegistry.FRAGMEN_POLEARM);
        entries.add(RCCommonRegistry.BROKEN_POLEARM);
        entries.add(RCCommonRegistry.DEVIL_FORTUNE);
        entries.add(RCCommonRegistry.TATTERED_PAPER);
    }
}
