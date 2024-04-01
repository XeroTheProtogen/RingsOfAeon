package keno.net.rings_of_aeon;

import dev.thomasglasser.sherdsapi.api.data.FabricSherdDatagenSuite;
import keno.net.rings_of_aeon.datagen.*;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

public class RCDataGenerator implements DataGeneratorEntrypoint {
    //TODO figure out what in god's name is causing SherdDatagenSuite to act weird
    private final FabricSherdDatagenSuite suite = new FabricSherdDatagenSuite(RuinousCall.MODID);

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModBlockTagGen::new);
        pack.addProvider(ModLangGen::new);
        pack.addProvider(ModModelsGen::new);
        pack.addProvider(ModRecipeGen::new);
        pack.addProvider(ModBlockLootProvider::new);
        registerSherds();
        suite.build(pack);
    }

    private void registerSherds() {
        //Register Sherd Data here
        suite.makeSherdSuite("fragmen", RCCommonRegistry.FRAGMEN_POTTERY_SHERD, "fragmen_pottery_pattern");
        suite.makeSherdSuite("cloud_owl", RCCommonRegistry.CLOUD_OWL_POTTERY_SHERD,"cloud_owl_pottery_pattern");
        suite.makeSherdSuite("unknown_cat", RCCommonRegistry.UNKNOWN_CAT_POTTERY_SHERD,"unknown_cat_pattern");
        suite.makeSherdSuite("human", RCCommonRegistry.HUMAN_POTTERY_SHERD, "human_pattern");
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        suite.buildRegistry(registryBuilder);
    }
}
