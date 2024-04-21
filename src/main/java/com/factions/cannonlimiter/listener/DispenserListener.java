package com.factions.cannonlimiter.listener;

import com.factions.cannonlimiter.chunk.ChunkLimiter;
import com.factions.cannonlimiter.registry.ChunkLimiterRegistry;
import com.factions.cannonlimiter.util.ChunkCoordinates;
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

    private final ChunkLimiterRegistry chunkLimiterRegistry;

    @EventHandler
    private void onPlace(BlockPlaceEvent event) {
        final Block block = event.getBlock();
        if (block.getType() != Material.DISPENSER) return;

        final Location location = block.getLocation();

        final Chunk chunk = location.getChunk();
        final ChunkCoordinates chunkCoordinates = new ChunkCoordinates(chunk.getX(), chunk.getZ());

        final ChunkLimiter chunkLimiter = chunkLimiterRegistry.getChunkLimiter(chunkCoordinates);
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
        final ChunkCoordinates chunkCoordinates = new ChunkCoordinates(chunk.getX(), chunk.getZ());

        final ChunkLimiter chunkLimiter = chunkLimiterRegistry.getChunkLimiter(chunkCoordinates);
        chunkLimiter.decreaseDispenser(1);
    }

    @EventHandler
    private void onExplode(EntityExplodeEvent event) {
        for (Block block : event.blockList()) {
            if (block.getType() != Material.DISPENSER) continue;

            final Location location = block.getLocation();

            final Chunk chunk = location.getChunk();
            final ChunkCoordinates chunkCoordinates = new ChunkCoordinates(chunk.getX(), chunk.getZ());

            final ChunkLimiter chunkLimiter = chunkLimiterRegistry.getChunkLimiter(chunkCoordinates);
            chunkLimiter.decreaseDispenser(1);
        }
    }
}
