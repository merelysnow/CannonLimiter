package com.factions.cannonlimiter.registry;

import com.factions.cannonlimiter.chunk.ChunkLimiter;
import com.factions.cannonlimiter.util.ChunkCoordinates;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class ChunkLimiterRegistry {

    private final Cache<ChunkCoordinates, ChunkLimiter> cache =
            Caffeine.newBuilder()
                    .build();

    public ChunkLimiter getChunkLimiter(@NotNull ChunkCoordinates chunkCoordinates) {
        return cache.asMap().computeIfAbsent(chunkCoordinates, $ -> new ChunkLimiter(chunkCoordinates));
    }

    public void removeChunkLimiter(@NotNull ChunkCoordinates chunkCoordinates) {
        cache.invalidate(chunkCoordinates);
    }

    public Collection<ChunkLimiter> getValues() {
        return cache.asMap().values();
    }
}
