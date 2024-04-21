package keno.net.rings_of_aeon;

import keno.net.rings_of_aeon.registries.RCTileEntities;
import keno.net.rings_of_aeon.client.blocks.altar_of_wealth.AltarOfWealthEntityRenderer;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class RCClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(RCCommonRegistry.CLOUD_OWL_STATUE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(RCCommonRegistry.UNKNOWN_CAT_SKULL, RenderLayer.getCutout());

        BlockEntityRendererFactories.register(RCTileEntities.ALTAR_OF_WEALTH, AltarOfWealthEntityRenderer::new);
    }
}
