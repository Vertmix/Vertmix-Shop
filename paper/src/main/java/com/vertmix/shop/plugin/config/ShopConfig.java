package com.vertmix.shop.plugin.config;

import com.vertmix.shop.api.Pair;
import com.vertmix.shop.common.item.CustomItemImpl;
import com.vertmix.shop.common.item.ShopItemImpl;
import com.vertmix.shop.common.wrapper.ShopWrapper;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ShopConfig {

    public ShopWrapper shopWrapper = new ShopWrapper("shop", "&8Shop", "vault",new HashMap<>() {{
        put("blocks", new Pair<>(new CustomItemImpl(Material.STONE, 0, "&bBlocks", List.of("&7Click me")), 1));
    }}, new HashMap<>() {{
        put("blocks", new HashSet<>() {{
            add(new ShopItemImpl(Material.STONE, 0, "&bBlocks", List.of("&7Click me"), 100, 20, List.of("eco give {player} 0")));
        }});
    }},"&a(+) You have purchased {amount}x of {item}.",
            Sound.ENTITY_ITEM_PICKUP,
            "&a(+) You have sold {amount}x of {item}.",
            Sound.ITEM_BUNDLE_DROP_CONTENTS,
            "&c(!) You do not have enough money!",
            Sound.ENTITY_ENDERMAN_TELEPORT,
            "&c(!) You do not have any of these items!",
            Sound.ENTITY_ENDERMAN_TELEPORT
    );

}
