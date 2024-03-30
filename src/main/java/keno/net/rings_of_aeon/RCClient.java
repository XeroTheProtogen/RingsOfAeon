package keno.net.rings_of_aeon;

import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class RCClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(RCCommonRegistry.CLOUD_OWL_STATUE, RenderLayer.getCutout());
    }
}
