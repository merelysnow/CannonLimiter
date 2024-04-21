package com.factions.cannonlimiter.task;

import com.factions.cannonlimiter.chunk.ChunkLimiter;
import com.factions.cannonlimiter.registry.ChunkLimiterRegistry;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChunkLimiterTask implements Runnable {

    private final ChunkLimiterRegistry chunkLimiterRegistry;

    @Override
    public void run() {
        for (ChunkLimiter chunkLimiter : chunkLimiterRegistry.getValues()) {
            if (chunkLimiter.getResetAt() > System.currentTimeMillis()) continue;

            if (chunkLimiter.getDispenserAmount() <= 0) {
                chunkLimiterRegistry.removeChunkLimiter(chunkLimiter.getChunkCoordinates());
                continue;
            }

            if (chunkLimiter.getIntervalTicks() > 0) {
                chunkLimiter.setIntervalTicks(0);
            }

            chunkLimiter.resetTime();
        }
    }
}
