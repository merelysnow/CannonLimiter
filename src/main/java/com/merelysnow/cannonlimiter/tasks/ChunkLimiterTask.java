package com.merelysnow.cannonlimiter.tasks;

import com.merelysnow.cannonlimiter.cache.ChunkCache;
import com.merelysnow.cannonlimiter.models.ChunkLimiter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChunkLimiterTask implements Runnable {

    private final ChunkCache chunkCache;

    @Override
    public void run() {
        for (ChunkLimiter chunkLimiter : chunkCache.getValues()) {
            if (chunkLimiter.getResetAt() > System.currentTimeMillis()) continue;

            if (chunkLimiter.getDispenserAmount() <= 0) {
                chunkCache.invalidate(chunkLimiter.getChunkCoordinates());
                continue;
            }


            if (chunkLimiter.getIntervalTicks() > 0) {
                chunkLimiter.setIntervalTicks(0);
            }

            chunkLimiter.resetTime();
        }
    }
}
