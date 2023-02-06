package com.vertmix.shop.api.item;

import me.lucko.helper.Helper;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface ShopItem extends SellableItem, PurchasableItem {

    @NotNull NamespacedKey SHOP_ITEM_KEY = new NamespacedKey(Helper.hostPlugin(), "SHOP_ITEM_KEY");

    @NotNull Collection<String> getCommands();
}
