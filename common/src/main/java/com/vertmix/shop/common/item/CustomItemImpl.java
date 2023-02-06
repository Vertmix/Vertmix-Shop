package com.vertmix.shop.common.item;

import com.vertmix.shop.api.item.CustomItem;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomItemImpl implements CustomItem {

    private final Material material;
    private final int customModelData;

    private final String displayName;
    private final List<String> lore;

    private final boolean custom;

    public CustomItemImpl(Material material, int customModelData, String displayName, List<String> lore) {
        this(material, customModelData, displayName, lore, false);
    }

    public CustomItemImpl(Material material, int customModelData, String displayName, List<String> lore, boolean custom) {
        this.material = material;
        this.customModelData = customModelData;
        this.displayName = displayName;
        this.lore = lore;
        this.custom = custom;
    }

    @Override
    public @NotNull Material getType() {
        return this.material;
    }

    @Override
    public int getCustomModelData() {
        return this.customModelData;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @NotNull
    @Override
    public List<String> getLore() {
        return this.lore;
    }

    @Override
    public boolean isCustom() {
        return custom;
    }
}
