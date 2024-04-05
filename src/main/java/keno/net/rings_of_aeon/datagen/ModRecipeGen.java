package keno.net.rings_of_aeon.datagen;

import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ModRecipeGen extends FabricRecipeProvider {
    public ModRecipeGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        //If a recipe shouldn't be used as a template, use a custom recipe builder
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, RCCommonRegistry.RELIQUIA_BRICKS, 4)
                .pattern("AAA")
                .pattern("ABC")
                .pattern("CCC")
                .input('A', Items.SAND)
                .input('B', Items.FLINT)
                .input('C', Items.GRAVEL)
                .criterion(hasItem(Items.SAND), conditionsFromItem(Items.SAND))
                .criterion(hasItem(Items.FLINT), conditionsFromItem(Items.FLINT))
                .criterion(hasItem(Items.GRAVEL), conditionsFromItem(Items.GRAVEL))
                .offerTo(exporter, new Identifier(getRecipeName(RCCommonRegistry.RELIQUIA_BRICKS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, RCCommonRegistry.FRAGMEN_POLEARM, 1)
                .pattern("X")
                .pattern("Y")
                .pattern("Z")
                .input('X', RCCommonRegistry.BROKEN_POLEARM)
                .input('Y', Items.CHAIN)
                .input('Z', ItemTags.LOGS)
                .criterion(hasItem(RCCommonRegistry.BROKEN_POLEARM), conditionsFromItem(RCCommonRegistry.BROKEN_POLEARM))
                .offerTo(exporter, new Identifier(getRecipeName(RCCommonRegistry.FRAGMEN_POLEARM)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, RCCommonRegistry.DEVIL_FORTUNE, 1)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.GLASS_BOTTLE)
                .input('B', RCCommonRegistry.TABLET_OF_WEALTH)
                .input('C', Items.REDSTONE)
                .criterion(hasItem(RCCommonRegistry.TABLET_OF_WEALTH), conditionsFromItem(RCCommonRegistry.TABLET_OF_WEALTH))
                .offerTo(exporter, new Identifier(getRecipeName(RCCommonRegistry.DEVIL_FORTUNE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, RCCommonRegistry.RELIQUIA_PILLAR, 2)
                .pattern("#")
                .pattern("#")
                .input('#', RCCommonRegistry.RELIQUIA_BRICKS)
                .criterion(hasItem(RCCommonRegistry.RELIQUIA_BRICKS), conditionsFromItem(RCCommonRegistry.RELIQUIA_BRICKS))
                .offerTo(exporter, new Identifier(getRecipeName(RCCommonRegistry.RELIQUIA_PILLAR)));

        //Blocks which have cookie-cutter recipes will either use fabric's offer methods or custom offer methods
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, RCCommonRegistry.RELIQUIA_SLAB, RCCommonRegistry.RELIQUIA_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, RCCommonRegistry.RELIQUIA_WALL, RCCommonRegistry.RELIQUIA_BRICKS);
        offerBasicRecipe(exporter, RecipeCategory.MISC, Items.BONE_MEAL, RCCommonRegistry.UNKNOWN_CAT_SKULL, 12);
        offerStairsRecipe(exporter, RCCommonRegistry.RELIQUIA_STAIRS, RCCommonRegistry.RELIQUIA_BRICKS);
        offerTableRecipe(exporter, RCCommonRegistry.ARCHIVIST_TABLE, ItemTags.PLANKS, RCCommonRegistry.TATTERED_PAPER);
    }

    private void offerTableRecipe(RecipeExporter exporter, @NotNull ItemConvertible output,
                                  @NotNull ItemConvertible base, @NotNull ItemConvertible material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output)
                .pattern("AA")
                .pattern("BB")
                .pattern("BB")
                .input('A', material)
                .input('B', base)
                .criterion(hasItem(material), conditionsFromItem(material))
                .offerTo(exporter, new Identifier(getRecipeName(output)));
    }

    private void offerTableRecipe(RecipeExporter exporter, @NotNull ItemConvertible output,
                                  @NotNull TagKey<Item> base, @NotNull ItemConvertible material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output)
                .pattern("AA")
                .pattern("BB")
                .pattern("BB")
                .input('A', material)
                .input('B', Ingredient.fromTag(base))
                .criterion(hasItem(material), conditionsFromItem(material))
                .offerTo(exporter, new Identifier(getRecipeName(output)));
    }

    private void offerStairsRecipe(RecipeExporter exporter, @NotNull ItemConvertible output, @NotNull ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, new Identifier(getRecipeName(RCCommonRegistry.RELIQUIA_STAIRS)));
    }

    private void offerBasicRecipe(RecipeExporter exporter,RecipeCategory category, @NotNull ItemConvertible output, @NotNull ItemConvertible input, int amount) {
        ShapedRecipeJsonBuilder.create(category, output, amount)
                .pattern("X")
                .input('X', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, new Identifier(getRecipeName(input)));
    }
}
