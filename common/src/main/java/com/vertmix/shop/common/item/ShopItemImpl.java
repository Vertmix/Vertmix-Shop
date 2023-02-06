package com.vertmix.shop.common.item;

import com.vertmix.shop.api.item.ShopItem;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShopItemImpl extends CustomItemImpl implements ShopItem {

    private final double buyPrice, sellPrice;
    private final List<String> commands;

    public ShopItemImpl(Material material, int customModelData, String displayName, List<String> lore, double buyPrice, double sellPrice, List<String> commands) {
        super(material, customModelData, displayName, lore);
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.commands = commands;
    }

    @Override
    public double getPurchasePrice() {
        return this.buyPrice;
    }

    @Override
    public double getSellPrice() {
        return this.sellPrice;
    }

    @NotNull
    @Override
    public List<String> getCommands() {
        return this.commands;
    }

}
