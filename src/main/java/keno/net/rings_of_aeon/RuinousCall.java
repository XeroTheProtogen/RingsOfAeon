package keno.net.rings_of_aeon;

import keno.net.rings_of_aeon.effects.RCStatusEffects;
import keno.net.rings_of_aeon.items.ModItemGroup;
import keno.net.rings_of_aeon.registries.RCCommonRegistry;
import keno.net.rings_of_aeon.registries.RCVillagerTypes;
import keno.net.rings_of_aeon.registries.RCVillagerTrades;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuinousCall implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("rings_of_aeon");
    public static final String MODID = "rings_of_aeon";

    @Override
    public void onInitialize() {
        RCCommonRegistry.registerCommonSide();
        RCVillagerTypes.register();
        RCVillagerTrades.registerTrades();
        ModItemGroup.registerItemGroups();
        RCStatusEffects.registerEffects();
    }

    public static Identifier modLoc(String location) {
        return new Identifier(MODID, location);
    }
}