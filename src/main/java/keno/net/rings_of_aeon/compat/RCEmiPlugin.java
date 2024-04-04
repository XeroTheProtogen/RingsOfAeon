package keno.net.rings_of_aeon.compat;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.stack.EmiStack;
import keno.net.rings_of_aeon.RuinousCall;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.Text;

import java.util.List;

public class RCEmiPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        addDescription(registry, RCCommonRegistry.BLOOD_RUSH, "blood_rush");
        addDescription(registry, RCCommonRegistry.FRAGMEN_POLEARM, "fragmen_polearm");
        addDescription(registry, RCCommonRegistry.DEVIL_FORTUNE, "devil_fortune");
        addDescription(registry, RCCommonRegistry.CLOUD_OWL_STATUE, "cloud_owl_statue");
        addDescription(registry, RCCommonRegistry.UNKNOWN_CAT_SKULL, "unknown_cat_skull");
    }

    //TODO Figure out a way to refer to the item's registry id dynamically
    private void addDescription(EmiRegistry registry, ItemConvertible convertible, String id) {
        var info = new EmiInfoRecipe(List.of(EmiStack.of(convertible)),
                List.of(Text.translatable("info.rings_of_aeon." + id)),
                RuinousCall.modLoc("info/" + id));
        registry.addRecipe(info);
    }
}
