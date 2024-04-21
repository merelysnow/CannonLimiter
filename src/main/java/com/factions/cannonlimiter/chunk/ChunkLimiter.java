package com.factions.cannonlimiter.chunk;

import com.factions.cannonlimiter.util.ChunkCoordinates;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
public class ChunkLimiter {

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

    public void warn(@NotNull Chunk chunk, String... message) {
        final List<Entity> entities = Arrays.stream(chunk.getEntities())
                .filter(entity -> entity instanceof Player)
                .collect(Collectors.toList());
        if (entities.isEmpty()) return;

        entities.forEach(player -> player.sendMessage(message));
    }

}
