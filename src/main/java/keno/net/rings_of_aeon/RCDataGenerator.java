package keno.net.rings_of_aeon;

import dev.thomasglasser.sherdsapi.api.data.FabricSherdDatagenSuite;
import keno.net.rings_of_aeon.datagen.ModBlockTagGen;
import keno.net.rings_of_aeon.datagen.ModLangGen;
import keno.net.rings_of_aeon.datagen.ModModelsGen;
import keno.net.rings_of_aeon.datagen.ModRecipeGen;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

import java.util.List;

public class RCDataGenerator implements DataGeneratorEntrypoint {
    private final FabricSherdDatagenSuite suite = new FabricSherdDatagenSuite(RuinousCall.MODID);

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModBlockTagGen::new);
        pack.addProvider(ModLangGen::new);
        pack.addProvider(ModModelsGen::new);
        pack.addProvider(ModRecipeGen::new);
        registerSherds();
        suite.build(pack);
    }

    private void registerSherds() {
        //Register Sherd Data here
        suite.makeSherdSuite(RuinousCall.modLoc("fragmen"), getSherdItems(RCCommonRegistry.FRAGMEN_POTTERY_SHERD),
                RuinousCall.modLoc("fragmen_pottery_pattern"));
        suite.makeSherdSuite(RuinousCall.modLoc("cloud_owl"), getSherdItems(RCCommonRegistry.CLOUD_OWL_POTTERY_SHERD),
                RuinousCall.modLoc("cloud_owl_pottery_pattern"));
    }

    private <E> List<E> getSherdItems(E item) {
        return List.of(item);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        suite.buildRegistry(registryBuilder);
    }
}
