package keno.net.rings_of_aeon.registries;

import keno.net.rings_of_aeon.RuinousCall;
import keno.net.rings_of_aeon.blocks.CloudOwlStatueBlock;
import keno.net.rings_of_aeon.blocks.UnknownCatSkullBlock;
import keno.net.rings_of_aeon.items.BloodRushItem;
import keno.net.rings_of_aeon.items.DevilsFortuneItem;
import keno.net.rings_of_aeon.items.FragmenPolearmItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class RCCommonRegistry {
    // Items
    // Weapons
    public static final Item BLOOD_RUSH = registerItem("blood_rush",
            new BloodRushItem(ToolMaterials.IRON, 4, -2.4f,
                    new FabricItemSettings().maxCount(1).maxDamage(255).fireproof()));
    public static final Item DEVIL_FORTUNE = registerItem("devil_fortune",
            new DevilsFortuneItem(ToolMaterials.STONE, 3, -2.3f,
                    new FabricItemSettings().maxCount(1).maxDamage(128).fireproof()));
    public static final Item FRAGMEN_POLEARM = registerItem("fragmen_polearm",
            new FragmenPolearmItem(ToolMaterials.IRON, 2, -2.6f,
                    new FabricItemSettings().maxCount(1).maxDamage(600).fireproof()));
    // Materials
    public static final Item BROKEN_POLEARM = registerItem("broken_polearm",
            new Item(new FabricItemSettings().fireproof()));
    public static final Item TABLET_OF_WEALTH = registerBasicItem("tablet_of_wealth");
    
    //Sherds
    public static final Item FRAGMEN_POTTERY_SHERD = registerBasicItem("fragmen_sherd");
    public static final Item CLOUD_OWL_POTTERY_SHERD = registerBasicItem("cloud_owl_sherd");
    public static final Item UNKNOWN_CAT_POTTERY_SHERD = registerBasicItem("unknown_cat_sherd"); 
    public static final Item HUMAN_POTTERY_SHERD = registerBasicItem("human_sherd");

    //Blocks
    static final FabricBlockSettings RELIQUIA_DEFAULT = FabricBlockSettings.copyOf(Blocks.BRICKS).sounds(BlockSoundGroup.DEEPSLATE_BRICKS).mapColor(MapColor.TERRACOTTA_RED).requiresTool();
    public static final Block RELIQUIA_PILLAR = registerBlock("reliquia_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block RELIQUIA_BRICKS = registerBlock("reliquia_brick",
            new Block(RELIQUIA_DEFAULT));
    public static final Block RELIQUIA_STAIR = registerBlock("reliquia_stair",
            new StairsBlock(RCCommonRegistry.RELIQUIA_BRICKS.getDefaultState(), RELIQUIA_DEFAULT));
    public static final Block RELIQUIA_SLAB = registerBlock("reliquia_slab",
            new SlabBlock(RELIQUIA_DEFAULT));
    public static final Block RELIQUIA_WALL = registerBlock("reliquia_wall",
            new WallBlock(RELIQUIA_DEFAULT));
    public static final Block CLOUD_OWL_STATUE = registerBlock("cloud_owl_statue",
            new CloudOwlStatueBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).dynamicBounds()
                    .burnable().nonOpaque()));
    public static final Block UNKNOWN_CAT_SKULL = registerBlock("unknown_cat_skull",
            new UnknownCatSkullBlock(FabricBlockSettings.copyOf(Blocks.STONE).dynamicBounds()
                    .nonOpaque().sounds(BlockSoundGroup.BONE).requiresTool()));

    private static Item registerBasicItem(String name) {
        // If an item doesn't need any special settings or custom Item subclass (Example: Crafting materials),
        // register here
        return Registry.register(Registries.ITEM, RuinousCall.modLoc(name), new Item(new FabricItemSettings()));
    }
    
    private static Item registerItem(String name, Item item) {
        // If you need to add Item Settings or pass a custom Item subclass, register here
        return Registry.register(Registries.ITEM, RuinousCall.modLoc(name), item);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, RuinousCall.modLoc(name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, RuinousCall.modLoc(name),
                new BlockItem(block, new FabricItemSettings()));
    }

    //Initialization method
    public static void registerCommonSide() {
        RuinousCall.LOGGER.info("Registering Common Elements for " + RuinousCall.MODID);
    }
}
