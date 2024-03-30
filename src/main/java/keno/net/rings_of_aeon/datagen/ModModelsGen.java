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
        BlockStateModelGenerator.BlockTexturePool reliquiaBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(RCCommonRegistry.RELIQUIA_BRICK);
        blockStateModelGenerator.registerAxisRotated(RCCommonRegistry.RELIQUIA_PILLAR, new Identifier(RuinousCall.MODID, "block/reliquia_pillar"));
        reliquiaBrickTexturePool.stairs(RCCommonRegistry.RELIQUIA_STAIR);
        reliquiaBrickTexturePool.slab(RCCommonRegistry.RELIQUIA_SLAB);
        reliquiaBrickTexturePool.wall(RCCommonRegistry.RELIQUIA_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(RCCommonRegistry.FRAGMEN_POTTERY_SHERD, Models.GENERATED);
        itemModelGenerator.register(RCCommonRegistry.CLOUD_OWL_POTTERY_SHERD, Models.GENERATED);
        itemModelGenerator.register(RCCommonRegistry.BROKEN_POLEARM, Models.GENERATED);
    }
}
