package keno.net.rings_of_aeon.registries;

import dev.thomasglasser.tommylib.impl.platform.FabricBlockEntityHelper;
import keno.net.rings_of_aeon.RuinousCall;
import keno.net.rings_of_aeon.blocks.entities.AltarOfWealthTileEntity;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RCTileEntities {
    public static BlockEntityType<AltarOfWealthTileEntity> ALTAR_OF_WEALTH;

    public static void registerTileEntities() {
        ALTAR_OF_WEALTH = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                RuinousCall.modLoc("altar_of_wealth"),
                FabricBlockEntityTypeBuilder.create(AltarOfWealthTileEntity::new,
                        RCCommonRegistry.ALTAR_OF_WEALTH).build(null));
    }
}
