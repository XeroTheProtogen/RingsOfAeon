package keno.net.rings_of_aeon.datagen;

import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ModRecipeGen extends FabricRecipeProvider {
    public ModRecipeGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, RCCommonRegistry.RELIQUIA_BRICK, 1)
                .pattern("AAA")
                .pattern("ABC")
                .pattern("CCC")
                .input('A', Items.SAND)
                .input('B', Items.FLINT)
                .input('C', Items.GRAVEL)
                .criterion(hasItem(Items.SAND), conditionsFromItem(Items.SAND))
                .criterion(hasItem(Items.FLINT), conditionsFromItem(Items.FLINT))
                .criterion(hasItem(Items.GRAVEL), conditionsFromItem(Items.GRAVEL))
                .offerTo(exporter, new Identifier(getRecipeName(RCCommonRegistry.RELIQUIA_BRICK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, RCCommonRegistry.FRAGMEN_POLEARM, 1)
                .pattern("X")
                .pattern("Y")
                .pattern("Z")
                .input('X', RCCommonRegistry.BROKEN_POLEARM)
                .input('Y', Items.CHAIN)
                .input('Z', ItemTags.LOGS)
                .criterion(hasItem(RCCommonRegistry.BROKEN_POLEARM), conditionsFromItem(RCCommonRegistry.BROKEN_POLEARM))
                .offerTo(exporter, new Identifier(getRecipeName(RCCommonRegistry.FRAGMEN_POLEARM)));

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, RCCommonRegistry.RELIQUIA_SLAB, RCCommonRegistry.RELIQUIA_BRICK);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, RCCommonRegistry.RELIQUIA_WALL, RCCommonRegistry.RELIQUIA_BRICK);
        offerBasicRecipe(exporter, RecipeCategory.MISC, Items.BONE_MEAL, RCCommonRegistry.UNKNOWN_CAT_SKULL, 12);
        offerStairRecipe(RCCommonRegistry.RELIQUIA_STAIR, RCCommonRegistry.RELIQUIA_BRICK);
    }

    private void offerStairRecipe(@NotNull ItemConvertible output, @NotNull ItemConvertible input) {
        //Literally just a wrapper I made because the naming scheme for the stairs recipe gen annoys me
        createStairsRecipe(output, Ingredient.ofItems(input));
    }

    private void offerBasicRecipe(RecipeExporter exporter,RecipeCategory category, @NotNull ItemConvertible output, @NotNull ItemConvertible input, int amount) {
        ShapedRecipeJsonBuilder.create(category, output, amount)
                .pattern("X")
                .input('X', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, new Identifier(getRecipeName(input)));
    }
}
