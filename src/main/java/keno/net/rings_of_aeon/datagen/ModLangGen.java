package keno.net.rings_of_aeon.datagen;

import keno.net.rings_of_aeon.RuinousCall;
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
        generateEMIInfoLang(translationBuilder);
        translationBuilder.add("death.attack.tooFast", "%1$s Went Too Fast To Handle");
    }

    private void generateGroupLang(TranslationBuilder builder) {
        builder.add(ModItemGroup.RUIN_CALL_KEY, "Ruinous Call");
    }

    private void generateItemLang(TranslationBuilder builder) {
        builder.add(RCCommonRegistry.DEVIL_FORTUNE, "Devil's Fortune");
        builder.add(RCCommonRegistry.BLOOD_RUSH, "Blood Rush");
        builder.add(RCCommonRegistry.FRAGMEN_POLEARM, "Fragmen Polearm");
        builder.add(RCCommonRegistry.CLOUD_OWL_POTTERY_SHERD, "Cloud Owl Pottery Sherd");
        builder.add(RCCommonRegistry.FRAGMEN_POTTERY_SHERD, "Fragmen Pottery Sherd");
        builder.add(RCCommonRegistry.UNKNOWN_CAT_POTTERY_SHERD, "Unknown Cat Pottery Sherd");
        builder.add(RCCommonRegistry.HUMAN_POTTERY_SHERD, "Human Pottery Sherd");
        builder.add(RCCommonRegistry.BROKEN_POLEARM, "Broken Polearm");
        builder.add(RCCommonRegistry.TABLET_OF_WEALTH, "Tablet of Wealth");
        builder.add(RCCommonRegistry.TATTERED_PAPER, "Tattered Paper");
    }

    private void generateBlockLang(TranslationBuilder builder) {
        builder.add(RCCommonRegistry.RELIQUIA_BRICKS, "Reliquia Bricks");
        builder.add(RCCommonRegistry.RELIQUIA_SLAB, "Reliquia Slab");
        builder.add(RCCommonRegistry.RELIQUIA_WALL, "Reliquia Wall");
        builder.add(RCCommonRegistry.RELIQUIA_STAIRS, "Reliquia Stairs");
        builder.add(RCCommonRegistry.RELIQUIA_PILLAR, "Reliquia Pillar");
        builder.add(RCCommonRegistry.CLOUD_OWL_STATUE, "Cloud Owl Statue");
        builder.add(RCCommonRegistry.UNKNOWN_CAT_SKULL, "Unknown Cat Skull");
        builder.add(RCCommonRegistry.ARCHIVIST_TABLE, "Archivist Table");
    }

    private void generateEMIInfoLang(TranslationBuilder builder) {
        String BASE_KEY = "info." + RuinousCall.MODID + ".";
        builder.add(BASE_KEY + "blood_rush", "Ability: On right-click, be taken down to 2 hearts, get a damage multiplier and health back on next hit in return. Found in Blood Temples");
        builder.add(BASE_KEY + "fragmen_polearm", "Ability: Each attack increases total damage by one, every 5 hits gives speed buffs. Weapon takes damage after reaching swiftness 3, so right-click to slow down at cost of damage reset");
        builder.add(BASE_KEY + "devil_fortune", "Ability: On right-click, gain a random effect. 20% chance to get a positive or negative jackpot of effects");
        builder.add(BASE_KEY + "cloud_owl_statue", "Generates in Fragmen Fighting Grounds");
        builder.add(BASE_KEY + "unknown_cat_skull", "Generates in Blood Temples");
    }
}
