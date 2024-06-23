package com.merelysnow.cannonlimiter.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Data
public class ChunkLimiter {

    Logger logger = Logger.getLogger(getClass().getName());
    private final ChunkCoordinates chunkCoordinates;
    private double intervalTicks = 0;
    private double dispenserAmount = 0;

    private long resetAt = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(3L);

    public void increaseTicks(double ticks) {
        this.intervalTicks += ticks;
    }

    public void increaseDispenser(double amount) {
        this.dispenserAmount += amount;
    }

    public void decreaseDispenser(double amount) {
        this.dispenserAmount -= amount;
    }

    public void resetTime() {
        this.resetAt = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(3L);

        logger.info("[Chunk-logs] X: " + chunkCoordinates.getX() + " Z: " + chunkCoordinates.getZ() + " foi resetada");
    }

    public void warn(@NotNull Chunk chunk, String... message) {
        List<Entity> entities = Arrays.stream(chunk.getEntities())
                .filter(Player.class::isInstance)
                .toList();
        if (entities.isEmpty()) return;

        entities.forEach(player -> player.sendMessage(message));
    }

}
