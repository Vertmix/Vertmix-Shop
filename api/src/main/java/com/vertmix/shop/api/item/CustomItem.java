package com.vertmix.shop.api.item;

import me.lucko.helper.item.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CustomItem {

    @NotNull Material getType();

    int getCustomModelData();

    @NotNull String getDisplayName();

    @NotNull List<String> getLore();

    boolean isCustom();

    default String simpleHash() {
        return getType().name() + ":" + getCustomModelData();
    }

    default @NotNull ItemStack toItemStack() {
        return ItemStackBuilder.of(getType()).name(getDisplayName()).lore(getLore()).transformMeta(itemMeta -> itemMeta.setCustomModelData(getCustomModelData())).transformMeta(meta -> meta.getPersistentDataContainer().set(ShopItem.SHOP_ITEM_KEY, PersistentDataType.STRING, simpleHash())).build();
    }

    default boolean compare(@NotNull ItemStack o) {
        if (getType() != o.getType()) return false;
        if (!o.hasItemMeta()) return false;
        final ItemMeta meta = o.getItemMeta();
        if (getCustomModelData() != meta.getCustomModelData()) return false;
        if (!isCustom()) return true;
        if (!meta.getPersistentDataContainer().has(ShopItem.SHOP_ITEM_KEY, PersistentDataType.STRING)) return false;
        return meta.getPersistentDataContainer().get(ShopItem.SHOP_ITEM_KEY, PersistentDataType.STRING).equalsIgnoreCase(simpleHash());
    }
}
