package keno.net.rings_of_aeon;

import keno.net.rings_of_aeon.datagen.ModBlockTagGen;
import keno.net.rings_of_aeon.datagen.ModModelGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class RingsOfAeonDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelGen::new);
		pack.addProvider(ModBlockTagGen::new);
	}
}
