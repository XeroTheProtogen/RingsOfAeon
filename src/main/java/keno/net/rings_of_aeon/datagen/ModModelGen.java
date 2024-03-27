package keno.net.rings_of_aeon.datagen;

import keno.net.rings_of_aeon.RingsOfAeon;
import keno.net.rings_of_aeon.registries.ModCommonRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

public class ModModelGen extends FabricModelProvider {

    public ModModelGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool reliquiaBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModCommonRegistry.RELIQUIA_BRICK);
        blockStateModelGenerator.registerAxisRotated(ModCommonRegistry.RELIQUIA_PILLAR, new Identifier(RingsOfAeon.MODID, "block/reliquia_pillar"));
        reliquiaBrickTexturePool.stairs(ModCommonRegistry.RELIQUIA_STAIR);
        reliquiaBrickTexturePool.slab(ModCommonRegistry.RELIQUIA_SLAB);
        reliquiaBrickTexturePool.wall(ModCommonRegistry.RELIQUIA_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModCommonRegistry.FRAGMEN_POLEARM, Models.GENERATED);
    }
}
