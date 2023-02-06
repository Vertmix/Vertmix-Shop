package com.vertmix.shop.plugin.gateway;

import com.vertmix.shop.api.Gateway;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class VaultGateway implements Gateway {

    private Economy economy;

    public VaultGateway() {
        setupEconomy();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    @Override
    public boolean hasFunds(@NotNull UUID playerUuid, double funds) {
        return this.economy.has(Bukkit.getOfflinePlayer(playerUuid), funds);
    }

    @Override
    public void withdraw(@NotNull UUID playerUuid, double funds) {
        this.economy.withdrawPlayer(Bukkit.getOfflinePlayer(playerUuid), funds);
    }

    @Override
    public void deposit(@NotNull UUID playerUuid, double funds) {
        this.economy.depositPlayer(Bukkit.getOfflinePlayer(playerUuid), funds);

    }
}
