package keno.net.rings_of_aeon.datagen;

import keno.net.rings_of_aeon.RingsOfAeon;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.util.Identifier;

public class ModBlockModels extends FabricModelProvider {
    public ModBlockModels(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool reliquiaBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(RCCommonRegistry.RELIQUIA_BRICK);
        blockStateModelGenerator.registerAxisRotated(RCCommonRegistry.RELIQUIA_PILLAR, new Identifier(RingsOfAeon.MODID, "block/reliquia_pillar"));
        reliquiaBrickTexturePool.stairs(RCCommonRegistry.RELIQUIA_STAIR);
        reliquiaBrickTexturePool.slab(RCCommonRegistry.RELIQUIA_SLAB);
        reliquiaBrickTexturePool.wall(RCCommonRegistry.RELIQUIA_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }
}
