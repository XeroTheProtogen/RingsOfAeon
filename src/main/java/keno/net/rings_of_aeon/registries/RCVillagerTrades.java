package keno.net.rings_of_aeon.registries;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;

public class RCVillagerTrades {
    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(RCVillagerTypes.ARCHIVIST, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(RCCommonRegistry.TATTERED_PAPER, 3),
                            new ItemStack(RCCommonRegistry.FRAGMEN_ARCHIVE_VOL_ONE, 1),
                            1, 3, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(RCCommonRegistry.TATTERED_PAPER, 1),
                            new ItemStack(Items.EMERALD, 2),
                            5, 1, 0.15f));
                });
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(RCCommonRegistry.TABLET_OF_WEALTH, 1),
                    2, 2, 0.1f));
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(RCCommonRegistry.TATTERED_PAPER, 1),
                    3, 1, 0.2f));
        });
    }
}
