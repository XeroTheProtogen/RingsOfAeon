package keno.net.rings_of_aeon.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@Environment(EnvType.CLIENT)
public interface ItemRenderingHelper {
    //Interface for simplifying item renders
    default void handleItemTransformation(MatrixStack matrices, Vector3f translation, Vector3f scale, Quaternionf rotation) {
        matrices.translate(translation.x(), translation.y(), translation.z());
        matrices.scale(scale.x(), scale.y(), scale.z());
    }

    //If working with for loops, override this method
    default void handleItemTransformation(MatrixStack matrices, Vector3f translation, Vector3f scale, Quaternionf rotation, int i) {
        matrices.translate(translation.x(), translation.y(), translation.z());
        matrices.scale(scale.x(), scale.y(), scale.z());
    }
}
