package com.vertmix.shop.api;

import com.vertmix.shop.api.item.CustomItem;
import com.vertmix.shop.api.item.ShopItem;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public interface Shop {

    @NotNull String getId();

    @NotNull String getTitle();

    @NotNull String getGateway();

    @NotNull Map<String, Pair<CustomItem, Integer>> getCategoryIcons();

    @NotNull Map<String, Set<ShopItem>> getCategories();

    @NotNull String getShopPurchasedItem();
    @NotNull Sound getShopPurchasedItemSound();

    @NotNull String getShopSoldItem();
    @NotNull Sound getShopSoldItemSound();

    @NotNull String getShopPurchaseFailInvalidFunds();
    @NotNull Sound getShopPurchaseFailInvalidFundsSound();

    @NotNull String getShopSoldFailInvalidItems();
    @NotNull Sound getShopSoldFailInvalidItemsSound();
}
