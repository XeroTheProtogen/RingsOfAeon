package keno.net.rings_of_aeon.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemUtils {
    public static ItemStack getHeldItem(PlayerEntity entity, Item item) {
        if (!entity.getMainHandStack().isEmpty() && entity.getMainHandStack().getItem() == item) {
            return entity.getMainHandStack();
        } else if (!entity.getOffHandStack().isEmpty() && entity.getOffHandStack().getItem() == item) {
            return entity.getOffHandStack();
        }

        return ItemStack.EMPTY;
    }

    public static boolean hasPlayerStackInInv(PlayerEntity player, Item item) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack currentStack = player.getInventory().getStack(i);
            if (!currentStack.isEmpty() && currentStack.isOf(item)) {
                return true;
            }

        }
        return false;
    }

    public static int getFirstInventoryIndex(PlayerEntity player, Item item) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack currentStack = player.getInventory().getStack(i);
            if (!currentStack.isEmpty() && currentStack.isOf(item)) {
                return i;
            }
        }

        return -1;
    }
}
