package keno.net.rings_of_aeon.datagen;

import keno.net.rings_of_aeon.registries.ModCommonRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGen extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModCommonRegistry.RELIQUIA_PILLAR)
                .add(ModCommonRegistry.RELIQUIA_BRICK)
                .add(ModCommonRegistry.RELIQUIA_STAIR);
    }
}
