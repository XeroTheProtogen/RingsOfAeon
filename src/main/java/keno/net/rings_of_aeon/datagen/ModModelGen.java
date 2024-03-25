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
        blockStateModelGenerator.registerAxisRotated(ModCommonRegistry.RELIQUIA_PILLAR, new Identifier(RingsOfAeon.MODID, "block/reliquia_pillar"));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModCommonRegistry.DEVIL_FORTUNE, Models.GENERATED);
    }
}
