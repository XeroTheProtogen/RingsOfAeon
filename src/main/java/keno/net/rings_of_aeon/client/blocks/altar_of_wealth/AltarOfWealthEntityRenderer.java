package keno.net.rings_of_aeon.client.blocks.altar_of_wealth;

import keno.net.rings_of_aeon.blocks.AltarOfWealthBlock;
import keno.net.rings_of_aeon.blocks.entities.AltarOfWealthTileEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class AltarOfWealthEntityRenderer implements BlockEntityRenderer<AltarOfWealthTileEntity> {
    public AltarOfWealthEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(AltarOfWealthTileEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack renderStack = entity.getStack(0);
        
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bBright = world.getLightLevel(LightType.BLOCK ,pos);
        int sBright = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bBright, sBright);
    }
}
