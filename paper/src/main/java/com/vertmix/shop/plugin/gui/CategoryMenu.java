package com.vertmix.shop.plugin.gui;

import com.vertmix.shop.api.Shop;
import com.vertmix.shop.api.item.ShopItem;
import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.paginated.PaginatedGui;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryMenu extends PaginatedGui {

    public CategoryMenu(Player player, Shop shop, Set<ShopItem> items) {
        super(gui -> items.stream().map(item -> ItemStackBuilder.of(item.toItemStack()).build(() -> {
            new SelectedMenu(gui.getPlayer(), shop, item).open();
        })).collect(Collectors.toList()), player, PaginatedGuiBuilder.create().title("&8Category"));
    }
}
