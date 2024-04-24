package keno.net.rings_of_aeon.client.blocks.altar_of_wealth;

import keno.net.rings_of_aeon.blocks.AltarOfWealthBlock;
import keno.net.rings_of_aeon.blocks.entities.AltarOfWealthTileEntity;
import keno.net.rings_of_aeon.util.ItemRenderingHelper;
import net.minecraft.block.BlockState;
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
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class AltarOfWealthEntityRenderer implements BlockEntityRenderer<AltarOfWealthTileEntity>, ItemRenderingHelper {
    public AltarOfWealthEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(AltarOfWealthTileEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack renderStack = entity.getStack(0);
        BlockState state = entity.getCachedState();
        for (int i = 1; i < state.get(AltarOfWealthBlock.ITEMS) + 1; i++) {
            //Pass as y for translation
            float center = 0.85f;
            Vector3f scaling = new Vector3f(0.4f, 0.4f, 0.4f);
            switch (i) {
                case 1 -> handleItemTransformation(matrices, new Vector3f(0.689f, center, 0.5f),
                        scaling, RotationAxis.NEGATIVE_X.rotationDegrees(28), i);
                case 2 -> handleItemTransformation(matrices, new Vector3f(0.5f, center, 0.689f),
                        scaling, RotationAxis.POSITIVE_X.rotationDegrees(28), i);
                case 3 -> handleItemTransformation(matrices, new Vector3f(0.313f, center, 0.5f),
                        scaling, RotationAxis.NEGATIVE_X.rotationDegrees(28), i);
                case 4 -> handleItemTransformation(matrices, new Vector3f(0.5f, center, 0.313f),
                        scaling, RotationAxis.POSITIVE_X.rotationDegrees(28), i);
            }
            itemRenderer.renderItem(renderStack, ModelTransformationMode.GUI, getLightLevel(entity.getWorld(),
                    entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }
    }



    private int getLightLevel(World world, BlockPos pos) {
        int bBright = world.getLightLevel(LightType.BLOCK ,pos);
        int sBright = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bBright, sBright);
    }

    @Override
    public void handleItemTransformation(MatrixStack matrices, Vector3f translation, Vector3f scale, Quaternionf rotation,int i) {
        matrices.push();
        ItemRenderingHelper.super.handleItemTransformation(matrices, translation, scale, rotation, i);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90 * i));
        matrices.multiply(rotation);
    }
}
