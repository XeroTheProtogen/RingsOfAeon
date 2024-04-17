package keno.net.rings_of_aeon.registries;

import com.google.common.collect.ImmutableSet;
import keno.net.rings_of_aeon.RuinousCall;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class RCVillagerTypes {
    public static final RegistryKey<PointOfInterestType> ARCHIVE_TABLE_KEY = poiKey("archivepoi");
    public static final PointOfInterestType ARCHIVE_POI = registerPoi("archivepoi", RCCommonRegistry.ARCHIVIST_TABLE);
    public static final VillagerProfession ARCHIVIST = registerProfession("archivist", ARCHIVE_TABLE_KEY);

    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, RuinousCall.modLoc(name),
                new VillagerProfession(name, entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN));
    }

    private static PointOfInterestType registerPoi(String name, Block block) {
        return PointOfInterestHelper.register(RuinousCall.modLoc(name), 1, 1, block);
    }
    private static RegistryKey<PointOfInterestType> poiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, RuinousCall.modLoc(name));
    }

    public static void register() {
        RuinousCall.LOGGER.info(RuinousCall.MODID + ": Registering Villagers");
    }
}
