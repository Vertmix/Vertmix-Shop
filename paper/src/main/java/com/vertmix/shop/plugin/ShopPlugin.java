package com.vertmix.shop.plugin;

import com.vertmix.config.ConfigRegistry;
import com.vertmix.config.json.JsonConfigRegistry;
import com.vertmix.shop.api.registry.GatewayRegistry;
import com.vertmix.shop.api.registry.ShopRegistry;
import com.vertmix.shop.api.service.ShopService;
import com.vertmix.shop.common.ShopImpl;
import com.vertmix.shop.common.registry.GatewayRegistryImpl;
import com.vertmix.shop.common.registry.ShopRegistryImpl;
import com.vertmix.shop.common.service.ShopServiceImpl;
import com.vertmix.shop.plugin.command.ShopCommand;
import com.vertmix.shop.plugin.config.ShopConfig;
import com.vertmix.shop.plugin.gateway.VaultGateway;
import me.lucko.helper.plugin.ExtendedJavaPlugin;

import java.io.File;
import java.util.HashSet;

import java.util.Set;

public class ShopPlugin extends ExtendedJavaPlugin {

    @Override
    protected void enable() {
        final ShopRegistry shopRegistry = provideService(ShopRegistry.class, new ShopRegistryImpl());
        final GatewayRegistry gatewayRegistry = provideService(GatewayRegistry.class, new GatewayRegistryImpl());
        final ShopService shopService = provideService(ShopService.class, new ShopServiceImpl(gatewayRegistry));


        getDataFolder().mkdir();
        final Set<ShopConfig> shopConfigs = loadConfigs();
        shopConfigs.forEach(shopConfig -> shopRegistry.add(shopConfig.shopWrapper.getShopId(), new ShopImpl(shopConfig.shopWrapper)));

        /*
         * Register payment gateways
         */
        gatewayRegistry.add("vault", new VaultGateway());


        bindModule(new ShopCommand(shopRegistry));
    }

    private Set<ShopConfig> loadConfigs() {
        final ConfigRegistry configRegistry = new JsonConfigRegistry();
        final File[] files = getDataFolder().listFiles();
        final Set<ShopConfig> configs = new HashSet<>();
        if (files == null || files.length == 0) {
            final ShopConfig config = configRegistry.register(ShopConfig.class, new ShopConfig(),  new File(getDataFolder(), "default.json"));
            configs.add(config);
        } else {
            for (File file : files) {
                if (file.getName().endsWith(".json")) {
                    final ShopConfig shopConfig = configRegistry.register(ShopConfig.class, new ShopConfig(), new File(getDataFolder(), file.getName()));
                    configs.add(shopConfig);
                }
            }
        }

       return configs;
    }
}
