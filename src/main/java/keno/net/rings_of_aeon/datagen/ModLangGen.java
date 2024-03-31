package keno.net.rings_of_aeon.datagen;

import keno.net.rings_of_aeon.items.ModItemGroup;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModLangGen extends FabricLanguageProvider {
    public ModLangGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        generateItemLang(translationBuilder);
        generateBlockLang(translationBuilder);
        generateGroupLang(translationBuilder);
        translationBuilder.add("death.attack.tooFast", "%1$s Went Too Fast To Handle");
    }

    private void generateGroupLang(TranslationBuilder builder) {
        builder.add(ModItemGroup.RUIN_CALL_KEY, "Ruinous Call");
    }

    private void generateItemLang(TranslationBuilder builder) {
        builder.add(RCCommonRegistry.DEVIL_FORTUNE, "Devil's Fortune");
        builder.add(RCCommonRegistry.BLOOD_RUSH, "Blood Rush");
        builder.add(RCCommonRegistry.FRAGMEN_POLEARM, "Fragmen Spear");
        builder.add(RCCommonRegistry.CLOUD_OWL_POTTERY_SHERD, "Cloud Owl Pottery Sherd");
        builder.add(RCCommonRegistry.FRAGMEN_POTTERY_SHERD, "Fragmen Pottery Sherd");
        builder.add(RCCommonRegistry.UNKNOWN_CAT_POTTERY_SHERD, "Unknown Cat Pottery Sherd");
        builder.add(RCCommonRegistry.HUMAN_POTTERY_SHERD, "Human Pottery Sherd");
        builder.add(RCCommonRegistry.BROKEN_POLEARM, "Broken Polearm");
    }

    private void generateBlockLang(TranslationBuilder builder) {
        builder.add(RCCommonRegistry.RELIQUIA_BRICK, "Reliquia Brick");
        builder.add(RCCommonRegistry.RELIQUIA_SLAB, "Reliquia Slab");
        builder.add(RCCommonRegistry.RELIQUIA_WALL, "Reliquia Wall");
        builder.add(RCCommonRegistry.RELIQUIA_STAIR, "Reliquia Stairs");
        builder.add(RCCommonRegistry.RELIQUIA_PILLAR, "Reliquia Pillar");
        builder.add(RCCommonRegistry.CLOUD_OWL_STATUE, "Cloud Owl Statue");
        builder.add(RCCommonRegistry.UNKNOWN_CAT_SKULL, "Unknown Cat Skull");
    }
}
