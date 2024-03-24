package keno.net.rings_of_aeon.registries;

import keno.net.rings_of_aeon.RingsOfAeon;
import keno.net.rings_of_aeon.items.BloodRushItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModCommonRegistry {
    public static final Item BLOOD_RUSH = registerItems("blood_rush",
            new BloodRushItem(ToolMaterials.IRON, 6, 1.4f, new FabricItemSettings().maxCount(1).maxDamage(255)));

    public static final Block RELIQUIA_PILLAR = registerBlock("reliquia_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));

    //Registry Methods
    private static Item registerItems(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(RingsOfAeon.MODID, name), item);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(RingsOfAeon.MODID, name),
                block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(RingsOfAeon.MODID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    //Initialization method
    public static void registerCommonSide() {
        RingsOfAeon.LOGGER.info("Registering Common Elements for " + RingsOfAeon.MODID);
    }
}
