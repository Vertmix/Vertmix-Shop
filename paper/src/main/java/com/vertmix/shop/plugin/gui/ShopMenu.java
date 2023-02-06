package com.vertmix.shop.plugin.gui;

import com.vertmix.shop.api.Shop;
import com.vertmix.shop.api.item.CustomItem;
import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Gui;
import org.bukkit.entity.Player;

public class ShopMenu extends Gui {
    private final Shop shop;

    public ShopMenu(Player player, Shop shop) {
        super(player, 5, shop.getTitle());
        this.shop = shop;
    }

    @Override
    public void redraw() {
        this.shop.getCategoryIcons().forEach((key, value) -> {
            final CustomItem customItem = value.key;
            setItem(value.value, ItemStackBuilder.of(customItem.toItemStack()).build(() -> {
                if (this.shop.getCategories().containsKey(key)) {
                    new CategoryMenu(getPlayer(), shop, this.shop.getCategories().get(key)).open();
                }
            }));
        });
    }
}
