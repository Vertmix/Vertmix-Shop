package com.vertmix.shop.plugin.command;

import com.vertmix.shop.api.registry.ShopRegistry;
import com.vertmix.shop.plugin.gui.ShopMenu;
import me.lucko.helper.Commands;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import org.jetbrains.annotations.NotNull;

public class ShopCommand implements TerminableModule {

    private final ShopRegistry shopRegistry;

    public ShopCommand(ShopRegistry shopRegistry) {
        this.shopRegistry = shopRegistry;
    }

    @Override
    public void setup(@NotNull TerminableConsumer consumer) {
        this.shopRegistry.values().forEach(shop -> Commands.create().assertPlayer().handler(c -> new ShopMenu(c.sender(), shop).open()).registerAndBind(consumer, shop.getId()));

    }
}
