package com.vertmix.shop.plugin.gui;

import com.vertmix.shop.api.Shop;
import com.vertmix.shop.api.item.ShopItem;
import com.vertmix.shop.api.service.ShopService;
import me.lucko.helper.Services;
import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Gui;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class SelectedMenu extends Gui {

    private static final NumberFormat numberFormat = NumberFormat.getInstance();

    private final Shop shop;
    private final ShopItem shopItem;
    private final ShopService service;

    public SelectedMenu(Player player, Shop shop, ShopItem shopItem) {
        super(player, 1, "&8Selection");
        this.shop = shop;
        this.shopItem = shopItem;
        this.service = Services.load(ShopService.class);
    }

    @Override
    public void redraw() {
        setPurchaseButton(1, 1);
        setPurchaseButton(2, 64);

        setItem(4, ItemStackBuilder.of(shopItem.toItemStack()).build(() -> {}));

        setSellButton(6, 64);
        setSellButton(7, 1);
    }

    private void setPurchaseButton(int slot, int q) {
        setItem(slot, ItemStackBuilder.of(Material.LIGHT_BLUE_STAINED_GLASS_PANE).name("&bBuy Item &7(" + q + "x)").build(() -> {
            if (service.buyItem(getPlayer(), shop, shopItem, q)) {
                getPlayer().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(shop.getShopPurchasedItem().replace("{item}", shopItem.getDisplayName()).replace("{amount}", numberFormat.format(64))));
                getPlayer().playSound(Sound.sound(builder -> builder.type(shop.getShopPurchasedItemSound())));
            }  else {
                getPlayer().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(shop.getShopPurchaseFailInvalidFunds()));
                getPlayer().playSound(Sound.sound(builder -> builder.type(shop.getShopPurchaseFailInvalidFundsSound())));
            }
        }));
    }

    private void setSellButton(int slot, int q) {
        setItem(slot, ItemStackBuilder.of(Material.GREEN_STAINED_GLASS_PANE).name("&aSell Item &7(" + q +"x)").build(() -> {
            if (service.sellItem(getPlayer(), shop, shopItem.toItemStack(), q)) {
                getPlayer().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(shop.getShopSoldItem().replace("{item}", shopItem.getDisplayName()).replace("{amount}", numberFormat.format(64))));
                getPlayer().playSound(Sound.sound(builder -> builder.type(shop.getShopSoldItemSound())));
            }  else {
                getPlayer().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(shop.getShopSoldFailInvalidItems()));
                getPlayer().playSound(Sound.sound(builder -> builder.type(shop.getShopSoldFailInvalidItemsSound())));
            }
        }));
    }
}
