package com.vertmix.shop.api.registry;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

public interface CustomRegistry<T> {

    void add(@NotNull String key, @NotNull T value);

    void remove(@NotNull String key);

    @NotNull Optional<T> get(@NotNull String key);

    @NotNull Collection<T> values();

    @NotNull Collection<String> keys();
}
