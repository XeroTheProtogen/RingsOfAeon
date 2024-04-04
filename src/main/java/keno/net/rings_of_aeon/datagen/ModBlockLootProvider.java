package keno.net.rings_of_aeon.datagen;

import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModBlockLootProvider extends FabricBlockLootTableProvider {
    public ModBlockLootProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(RCCommonRegistry.RELIQUIA_BRICKS);
        addDrop(RCCommonRegistry.RELIQUIA_PILLAR);
        addDrop(RCCommonRegistry.RELIQUIA_SLAB);
        addDrop(RCCommonRegistry.RELIQUIA_WALL);
        addDrop(RCCommonRegistry.RELIQUIA_STAIRS);
        addDrop(RCCommonRegistry.CLOUD_OWL_STATUE);
        addDrop(RCCommonRegistry.UNKNOWN_CAT_SKULL);
    }
}
