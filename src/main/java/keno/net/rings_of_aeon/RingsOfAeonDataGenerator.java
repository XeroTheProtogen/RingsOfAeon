package keno.net.rings_of_aeon;

import dev.thomasglasser.sherdsapi.api.data.FabricSherdDatagenSuite;
import keno.net.rings_of_aeon.datagen.ModModelsGen;
import keno.net.rings_of_aeon.datagen.ModBlockTagGen;
import keno.net.rings_of_aeon.datagen.ModLangGen;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

import java.util.List;

public class RingsOfAeonDataGenerator implements DataGeneratorEntrypoint {
	private final FabricSherdDatagenSuite suite = new FabricSherdDatagenSuite(RingsOfAeon.MODID);
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModBlockTagGen::new);
		pack.addProvider(ModLangGen::new);
		pack.addProvider(ModModelsGen::new);
		registerSherds();
		suite.build(pack);
	}

	private void registerSherds() {
		//Register Sherd Data here
		suite.makeSherdSuite(RingsOfAeon.modLoc("fragmen"), getSherdItems(RCCommonRegistry.FRAGMEN_POTTERY_SHERD),
				RingsOfAeon.modLoc("fragmen_pottery_pattern"));
		suite.makeSherdSuite(RingsOfAeon.modLoc("cloud_owl"), getSherdItems(RCCommonRegistry.CLOUD_OWL_POTTERY_SHERD),
				RingsOfAeon.modLoc("cloud_owl_pottery_pattern"));
	}

	private <E> List<E> getSherdItems(E item) {
		return List.of(item);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		suite.buildRegistry(registryBuilder);
	}
}
