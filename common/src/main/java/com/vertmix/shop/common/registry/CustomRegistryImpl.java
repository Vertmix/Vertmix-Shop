package com.vertmix.shop.common.registry;

import com.vertmix.shop.api.registry.CustomRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class CustomRegistryImpl<T> implements CustomRegistry<T> {

    private final Map<String, T> collection = new LinkedHashMap<>();

    @Override
    public void add(@NotNull String key, @NotNull T value) {
        this.collection.put(key, value);
    }

    @Override
    public void remove(@NotNull String key) {
        this.collection.remove(key);
    }

    @Override
    public @NotNull Optional<T> get(@NotNull String key) {
        return Optional.ofNullable(this.collection.get(key));
    }

    @Override
    public @NotNull Collection<String> keys() {
        return this.collection.keySet();
    }

    @Override
    public @NotNull Collection<T> values() {
        return this.collection.values();
    }
}
