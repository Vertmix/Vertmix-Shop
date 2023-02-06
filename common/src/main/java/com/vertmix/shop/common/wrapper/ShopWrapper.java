package com.vertmix.shop.common.wrapper;

import com.vertmix.shop.api.Pair;
import com.vertmix.shop.common.item.CustomItemImpl;
import com.vertmix.shop.common.item.ShopItemImpl;
import org.bukkit.Sound;

import java.util.Map;
import java.util.Set;

public class ShopWrapper {

    private final String shopId;
    private final String shopTitle;

    private final String gateway;

    private final Map<String, Pair<CustomItemImpl, Integer>> categoryIcons;
    private final Map<String, Set<ShopItemImpl>> categories;

    private final String shopPurchasedItem;
    private final Sound shopPurchasedItemSound;

    private final String shopSoldItem;
    private final Sound shopSoldItemSound;

    private final String shopPurchaseFailInvalidFunds;
    private final Sound shopPurchaseFailInvalidFundsSound;

    private final String shopSoldFailInvalidItems;
    private final Sound shopSoldFailInvalidItemsSound;

    public ShopWrapper(String shopId, String shopTitle, String gateway, Map<String, Pair<CustomItemImpl, Integer>> categoryIcons, Map<String, Set<ShopItemImpl>> categories, String shopPurchasedItem, Sound shopPurchasedItemSound, String shopSoldItem, Sound shopSoldItemSound, String shopPurchaseFailInvalidFunds, Sound shopPurchaseFailInvalidFundsSound, String shopSoldFailInvalidItems, Sound shopSoldFailInvalidItemsSound) {
        this.shopId = shopId;
        this.shopTitle = shopTitle;
        this.gateway = gateway;
        this.categoryIcons = categoryIcons;
        this.categories = categories;
        this.shopPurchasedItem = shopPurchasedItem;
        this.shopPurchasedItemSound = shopPurchasedItemSound;
        this.shopSoldItem = shopSoldItem;
        this.shopSoldItemSound = shopSoldItemSound;
        this.shopPurchaseFailInvalidFunds = shopPurchaseFailInvalidFunds;
        this.shopPurchaseFailInvalidFundsSound = shopPurchaseFailInvalidFundsSound;
        this.shopSoldFailInvalidItems = shopSoldFailInvalidItems;
        this.shopSoldFailInvalidItemsSound = shopSoldFailInvalidItemsSound;
    }



    public String getShopId() {
        return shopId;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public String getGateway() {
        return gateway;
    }

    public Map<String, Pair<CustomItemImpl, Integer>> getCategoryIcons() {
        return categoryIcons;
    }

    public Map<String, Set<ShopItemImpl>> getCategories() {
        return categories;
    }

    public String getShopPurchasedItem() {
        return shopPurchasedItem;
    }

    public Sound getShopPurchasedItemSound() {
        return shopPurchasedItemSound;
    }

    public String getShopSoldItem() {
        return shopSoldItem;
    }

    public Sound getShopSoldItemSound() {
        return shopSoldItemSound;
    }

    public String getShopPurchaseFailInvalidFunds() {
        return shopPurchaseFailInvalidFunds;
    }

    public Sound getShopPurchaseFailInvalidFundsSound() {
        return shopPurchaseFailInvalidFundsSound;
    }

    public String getShopSoldFailInvalidItems() {
        return shopSoldFailInvalidItems;
    }

    public Sound getShopSoldFailInvalidItemsSound() {
        return shopSoldFailInvalidItemsSound;
    }
}
