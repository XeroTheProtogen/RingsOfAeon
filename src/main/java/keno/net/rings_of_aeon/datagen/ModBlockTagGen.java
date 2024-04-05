package keno.net.rings_of_aeon.datagen;

import keno.net.rings_of_aeon.registries.RCCommonRegistry;
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
                .add(RCCommonRegistry.RELIQUIA_PILLAR)
                .add(RCCommonRegistry.RELIQUIA_BRICKS)
                .add(RCCommonRegistry.RELIQUIA_STAIRS)
                .add(RCCommonRegistry.RELIQUIA_SLAB)
                .add(RCCommonRegistry.RELIQUIA_WALL)
                .add(RCCommonRegistry.UNKNOWN_CAT_SKULL);
        getOrCreateTagBuilder(BlockTags.STONE_BRICKS)
                .add(RCCommonRegistry.RELIQUIA_BRICKS);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(RCCommonRegistry.ARCHIVIST_TABLE);
    }
}
