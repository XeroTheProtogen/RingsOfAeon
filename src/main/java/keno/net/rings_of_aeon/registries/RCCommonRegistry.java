package keno.net.rings_of_aeon.registries;

import keno.net.rings_of_aeon.RingsOfAeon;
import keno.net.rings_of_aeon.items.BloodRushItem;
import keno.net.rings_of_aeon.items.DevilsFortuneItem;
import keno.net.rings_of_aeon.items.FragmenPolearmItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class RCCommonRegistry {

    //Items
    public static final Item BLOOD_RUSH = registerItem("blood_rush",
            new BloodRushItem(ToolMaterials.IRON, 4, -2.4f, new FabricItemSettings().maxCount(1).maxDamage(255)));
    public static final Item DEVIL_FORTUNE = registerItem("devil_fortune",
            new DevilsFortuneItem(ToolMaterials.STONE, 3, -2.3f, new FabricItemSettings().maxCount(1).maxDamage(128)));
    public static final Item FRAGMEN_POLEARM = registerItem("fragmen_polearm",
            new FragmenPolearmItem(ToolMaterials.IRON, 2,-1.0f,  new FabricItemSettings().maxCount(1).maxDamage(510)));

    //Sherds
    public static final Item FRAGMEN_POTTERY_SHERD = registerItem("fragmen_sherd", new Item(new FabricItemSettings()));
    public static final Item CLOUD_OWL_POTTERY_SHERD = registerItem("cloud_owl_sherd", new Item(new FabricItemSettings()));

    //Blocks
    static final FabricBlockSettings RELIQUIA_DEFAULT = FabricBlockSettings.copyOf(Blocks.BRICKS).sounds(BlockSoundGroup.DEEPSLATE_BRICKS).mapColor(MapColor.STONE_GRAY);
    public static final Block RELIQUIA_PILLAR = registerBlock("reliquia_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block RELIQUIA_BRICK = registerBlock("reliquia_brick",
            new Block(RELIQUIA_DEFAULT));
    public static final Block RELIQUIA_STAIR = registerBlock("reliquia_stair",
            new StairsBlock(RCCommonRegistry.RELIQUIA_BRICK.getDefaultState(), RELIQUIA_DEFAULT));
    public static final Block RELIQUIA_SLAB = registerBlock("reliquia_slab",
            new SlabBlock(RELIQUIA_DEFAULT));
    public static final Block RELIQUIA_WALL = registerBlock("reliquia_wall",
            new WallBlock(RELIQUIA_DEFAULT));

    private static Item registerItem(String name, Item item) {
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
