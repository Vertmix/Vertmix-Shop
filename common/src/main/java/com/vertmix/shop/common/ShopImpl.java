package com.vertmix.shop.common;

import com.vertmix.shop.api.Pair;
import com.vertmix.shop.api.Shop;
import com.vertmix.shop.api.item.CustomItem;
import com.vertmix.shop.api.item.ShopItem;
import com.vertmix.shop.common.wrapper.ShopWrapper;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ShopImpl implements Shop {

    private final String id, title, gateway;
    private final Map<String, Pair<CustomItem, Integer>> categoryIcons;
    private final Map<String, Set<ShopItem>> categories;

    private final String shopPurchasedItem;
    private final Sound shopPurchasedItemSound;

    private final String shopSoldItem;
    private final Sound shopSoldItemSound;

    private final String shopPurchaseFailInvalidFunds;
    private final Sound shopPurchaseFailInvalidFundsSound;

    private final String shopSoldFailInvalidItems;
    private final Sound shopSoldFailInvalidItemsSound;
    public ShopImpl(ShopWrapper shopWrapper) {
        this.id = shopWrapper.getShopId();
        this.title = shopWrapper.getShopTitle();
        this.gateway = shopWrapper.getGateway();
        this.categories = new LinkedHashMap<>();
        this.categoryIcons = new HashMap<>();

        shopWrapper.getCategoryIcons().forEach(((key, item) -> this.categoryIcons.put(key, new Pair<>(item.key, item.value))));
        shopWrapper.getCategories().forEach(((key, item) -> this.categories.computeIfAbsent(key, $ -> new HashSet<>()).addAll(item)));

        this.shopPurchasedItem = shopWrapper.getShopPurchasedItem();
        this.shopPurchasedItemSound = shopWrapper.getShopPurchasedItemSound();

        this.shopSoldItem = shopWrapper.getShopSoldItem();
        this.shopSoldItemSound = shopWrapper.getShopSoldItemSound();

        this.shopPurchaseFailInvalidFunds = shopWrapper.getShopPurchaseFailInvalidFunds();
        this.shopPurchaseFailInvalidFundsSound = shopWrapper.getShopPurchaseFailInvalidFundsSound();


        this.shopSoldFailInvalidItems = shopWrapper.getShopSoldFailInvalidItems();
        this.shopSoldFailInvalidItemsSound = shopWrapper.getShopSoldFailInvalidItemsSound();

    }


    @Override
    public @NotNull String getId() {
        return this.id;
    }

    @Override
    public @NotNull String getTitle() {
        return this.title;
    }

    @NotNull
    @Override
    public String getGateway() {
        return gateway;
    }

    @Override
    public @NotNull Map<String, Set<ShopItem>> getCategories() {
        return this.categories;
    }

    @NotNull
    @Override
    public Map<String, Pair<CustomItem, Integer>> getCategoryIcons() {
        return categoryIcons;
    }

    @NotNull
    @Override
    public String getShopPurchasedItem() {
        return shopPurchasedItem;
    }

    @NotNull
    @Override
    public Sound getShopPurchasedItemSound() {
        return shopPurchasedItemSound;
    }

    @NotNull
    @Override
    public String getShopSoldItem() {
        return shopSoldItem;
    }

    @NotNull
    @Override
    public Sound getShopSoldItemSound() {
        return shopSoldItemSound;
    }

    @NotNull
    @Override
    public String getShopPurchaseFailInvalidFunds() {
        return shopPurchaseFailInvalidFunds;
    }

    @NotNull
    @Override
    public Sound getShopPurchaseFailInvalidFundsSound() {
        return shopPurchaseFailInvalidFundsSound;
    }

    @NotNull
    @Override
    public String getShopSoldFailInvalidItems() {
        return shopSoldFailInvalidItems;
    }

    @NotNull
    @Override
    public Sound getShopSoldFailInvalidItemsSound() {
        return shopSoldFailInvalidItemsSound;
    }
}
