package keno.net.rings_of_aeon.datagen;

import keno.net.rings_of_aeon.RuinousCall;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

public class ModModelsGen extends FabricModelProvider {
    public ModModelsGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool reliquiaBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(RCCommonRegistry.RELIQUIA_BRICKS);
        blockStateModelGenerator.registerAxisRotated(RCCommonRegistry.RELIQUIA_PILLAR, new Identifier(RuinousCall.MODID, "block/reliquia_pillar"));
        reliquiaBrickTexturePool.stairs(RCCommonRegistry.RELIQUIA_STAIRS);
        reliquiaBrickTexturePool.slab(RCCommonRegistry.RELIQUIA_SLAB);
        reliquiaBrickTexturePool.wall(RCCommonRegistry.RELIQUIA_WALL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(RCCommonRegistry.ALTAR_OF_WEALTH);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(RCCommonRegistry.CLOUD_OWL_STATUE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(RCCommonRegistry.UNKNOWN_CAT_SKULL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(RCCommonRegistry.FRAGMEN_POTTERY_SHERD, Models.GENERATED);
        itemModelGenerator.register(RCCommonRegistry.CLOUD_OWL_POTTERY_SHERD, Models.GENERATED);
        itemModelGenerator.register(RCCommonRegistry.UNKNOWN_CAT_POTTERY_SHERD, Models.GENERATED);
        itemModelGenerator.register(RCCommonRegistry.HUMAN_POTTERY_SHERD, Models.GENERATED);
        itemModelGenerator.register(RCCommonRegistry.BROKEN_POLEARM, Models.GENERATED);
        itemModelGenerator.register(RCCommonRegistry.TABLET_OF_WEALTH, Models.GENERATED);
        itemModelGenerator.register(RCCommonRegistry.TATTERED_PAPER, Models.GENERATED);
    }
}
