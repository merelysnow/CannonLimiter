package com.merelysnow.cannonlimiter.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.merelysnow.cannonlimiter.models.ChunkCoordinates;
import com.merelysnow.cannonlimiter.models.ChunkLimiter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ChunkCache {

    private final Cache<ChunkCoordinates, ChunkLimiter> cache = Caffeine.newBuilder().build();

    public ChunkLimiter getIfPresent(@NotNull ChunkCoordinates chunkCoordinates) {
        return cache.asMap().computeIfAbsent(chunkCoordinates, _ -> new ChunkLimiter(chunkCoordinates));
    }


    public void invalidate(@NotNull ChunkCoordinates chunkCoordinates) {
        cache.invalidate(chunkCoordinates);
    }

    public void invalidateAll() {
        this.cache.invalidateAll();
    }

    public Set<ChunkLimiter> getValues() {
        return Set.copyOf(cache.asMap().values());
    }
}
