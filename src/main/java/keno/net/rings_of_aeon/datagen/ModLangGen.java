package keno.net.rings_of_aeon.datagen;

import keno.net.rings_of_aeon.items.ModItemGroup;
import keno.net.rings_of_aeon.registries.ModCommonRegistry;
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
        translationBuilder.add("spear.damage", "Spear Damage");
        translationBuilder.add("death.attack.tooFast", "%1$s Went Too Fast To Handle");
    }

    private void generateGroupLang(TranslationBuilder builder) {
        builder.add(ModItemGroup.RUIN_CALL_KEY, "Ruinous Call");
    }

    private void generateItemLang(TranslationBuilder builder) {
        builder.add(ModCommonRegistry.DEVIL_FORTUNE, "Devil's Fortune");
        builder.add(ModCommonRegistry.BLOOD_RUSH, "Blood Rush");
        builder.add(ModCommonRegistry.FRAGMEN_POLEARM, "Fragmen Spear");
    }

    private void generateBlockLang(TranslationBuilder builder) {
        builder.add(ModCommonRegistry.RELIQUIA_BRICK, "Reliquia Brick");
        builder.add(ModCommonRegistry.RELIQUIA_SLAB, "Reliquia Slab");
        builder.add(ModCommonRegistry.RELIQUIA_WALL, "Reliquia Wall");
        builder.add(ModCommonRegistry.RELIQUIA_STAIR, "Reliquia Stairs");
        builder.add(ModCommonRegistry.RELIQUIA_PILLAR, "Reliquia Pillar");
    }
}
