package keno.net.rings_of_aeon.datagen;

import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

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
    }
}
