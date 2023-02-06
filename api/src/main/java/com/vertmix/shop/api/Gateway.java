package com.vertmix.shop.api;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface Gateway {

    boolean hasFunds(@NotNull UUID playerUuid, double funds);

    void withdraw(@NotNull UUID playerUuid, double funds);

    void deposit(@NotNull UUID playerUuid, double funds);

}
