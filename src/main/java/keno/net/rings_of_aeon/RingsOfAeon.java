package keno.net.rings_of_aeon;

import keno.net.rings_of_aeon.items.ModItemGroup;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RingsOfAeon implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("rings_of_aeon");
	public static final String MODID = "rings_of_aeon";

	@Override
	public void onInitialize() {
		RCCommonRegistry.registerCommonSide();
		ModItemGroup.registerItemGroups();
	}

	public static Identifier modLoc(String location) {
		return new Identifier(MODID, location);
	}
}