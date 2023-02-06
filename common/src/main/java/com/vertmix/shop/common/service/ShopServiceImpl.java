package com.vertmix.shop.common.service;

import com.vertmix.shop.api.Gateway;
import com.vertmix.shop.api.Shop;
import com.vertmix.shop.api.item.ShopItem;
import com.vertmix.shop.api.registry.GatewayRegistry;
import com.vertmix.shop.api.service.ShopService;
import me.lucko.helper.Helper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ShopServiceImpl implements ShopService {

    private final GatewayRegistry gatewayRegistry;

    public ShopServiceImpl(GatewayRegistry gatewayRegistry) {
        this.gatewayRegistry = gatewayRegistry;
    }

    @Override
    public boolean sellItem(@NotNull Player player, @NotNull Shop shop, @NotNull ItemStack itemStack, int amount) {
        final Optional<ShopItem> shopItemOpt = getFromItemStack(shop, itemStack);
        final Optional<Gateway> gatewayOpt = gatewayRegistry.get(shop.getGateway());
        if (shopItemOpt.isPresent() && gatewayOpt.isPresent()) {
            final ShopItem shopItem = shopItemOpt.get();
            final Gateway gateway = gatewayOpt.get();
            final double sellPrice = shopItem.getSellPrice();

            final PlayerInventory inventory = player.getInventory();
            final Set<ItemStack> target = new HashSet<>(amount);
            int totalCount = 0;
            for (int i = 0; i < 36; i++) {
                if (totalCount >= amount) break;

                final ItemStack itemStackAt = inventory.getItem(i);
                if (itemStackAt == null || itemStackAt.getType() == Material.AIR) continue;

                if (shopItem.compare(itemStackAt)) {
                    int left = amount - totalCount;
                    int j = (itemStackAt.getAmount() % left) == 0 ? left : itemStackAt.getAmount() / left;
                    if (j != left) {
                        itemStackAt.setAmount(itemStackAt.getAmount() - j);
                    }
                    target.add(itemStackAt);
                    totalCount += j;
                }
            }

            if (target.isEmpty()) {
                return false;
            }


            // remove from inventory
            player.getInventory().removeItem(target.toArray(new ItemStack[0]));
            gateway.deposit(player.getUniqueId(), sellPrice * target.stream().mapToInt(ItemStack::getAmount).sum());
            return true;
        }
        return false;
    }

    @Override
    public boolean buyItem(@NotNull Player player, @NotNull Shop shop, @NotNull ShopItem shopItem, int amount) {
        final Optional<Gateway> gatewayOpt = gatewayRegistry.get(shop.getGateway());
        if (gatewayOpt.isEmpty()) {
            return false;
        }

        final Gateway gateway = gatewayOpt.get();
        final double purchasePrice = shopItem.getPurchasePrice() * amount;
        if (!gateway.hasFunds(player.getUniqueId(), purchasePrice)) {
            return false;
        }

        gateway.withdraw(player.getUniqueId(), purchasePrice);
        shopItem.getCommands().forEach(command -> Helper.executeCommand(command.replace("{player}", player.getName())));

        final ItemStack itemStack = shopItem.toItemStack();
        itemStack.setAmount(amount);
        if (player.getInventory().firstEmpty() == -1) {
            player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
        } else player.getInventory().addItem(itemStack);

        return true;
    }

    @Override
    public @NotNull Optional<ShopItem> getFromItemStack(@NotNull Shop shop, @NotNull ItemStack itemStack) {
        return shop.getCategories().values().stream().flatMap(Collection::stream).filter(shopItem -> shopItem.compare(itemStack)).findFirst();
    }
}
