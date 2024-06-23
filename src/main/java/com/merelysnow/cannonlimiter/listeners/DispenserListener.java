package com.merelysnow.cannonlimiter.listeners;

import com.merelysnow.cannonlimiter.cache.ChunkCache;
import com.merelysnow.cannonlimiter.models.ChunkCoordinates;
import com.merelysnow.cannonlimiter.models.ChunkLimiter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

@RequiredArgsConstructor
public class DispenserListener implements Listener {

    private final ChunkCache chunkCache;

    @EventHandler
    private void onPlace(BlockPlaceEvent event) {
        final Block block = event.getBlock();
        if (block.getType() != Material.DISPENSER) return;

        final Location location = block.getLocation();

        final Chunk chunk = location.getChunk();
        final ChunkCoordinates chunkCoordinates = new ChunkCoordinates(chunk.getX(), chunk.getZ(), location.getWorld().getName());

        final ChunkLimiter chunkLimiter = chunkCache.getIfPresent(chunkCoordinates);
        if (chunkLimiter.getDispenserAmount() >= 90) {
            final Player player = event.getPlayer();

            player.sendMessage("§cEssa chunk já possui 90 ejetores colocados.");
            event.setCancelled(true);
            return;
        }

        chunkLimiter.increaseDispenser(1);
    }

    @EventHandler
    private void onBreak(BlockBreakEvent event) {
        final Block block = event.getBlock();
        if (block.getType() != Material.DISPENSER) return;

        final Location location = block.getLocation();

        final Chunk chunk = location.getChunk();
        final ChunkCoordinates chunkCoordinates = new ChunkCoordinates(chunk.getX(), chunk.getZ(), location.getWorld().getName());

        final ChunkLimiter chunkLimiter = chunkCache.getIfPresent(chunkCoordinates);
        chunkLimiter.decreaseDispenser(1);
    }

    @EventHandler
    private void onExplode(EntityExplodeEvent event) {
        for (Block block : event.blockList()) {
            if (block.getType() != Material.DISPENSER) continue;

            final Location location = block.getLocation();

            final Chunk chunk = location.getChunk();
            final ChunkCoordinates chunkCoordinates = new ChunkCoordinates(chunk.getX(), chunk.getZ(), chunk.getWorld().getName());

            final ChunkLimiter chunkLimiter = chunkCache.getIfPresent(chunkCoordinates);
            chunkLimiter.decreaseDispenser(1);
        }
    }
}
