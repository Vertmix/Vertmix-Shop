package com.vertmix.shop.api.service;

import com.vertmix.shop.api.Shop;
import com.vertmix.shop.api.item.ShopItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ShopService {

    boolean sellItem(@NotNull Player player, @NotNull Shop shop, @NotNull ItemStack itemStack, int amount);

    default boolean sellItem(@NotNull Player player, @NotNull Shop shop, @NotNull ItemStack itemStack) {
        return sellItem(player, shop, itemStack, 1);
    }

    boolean buyItem(@NotNull Player player, @NotNull Shop shop, @NotNull ShopItem shopItem, int amount);

    default boolean buyItem(@NotNull Player player, @NotNull Shop shop, @NotNull ShopItem shopItem) {
        return buyItem(player, shop, shopItem, 1);
    }


    @NotNull Optional<ShopItem> getFromItemStack(@NotNull Shop shop, @NotNull ItemStack itemStack);
}
