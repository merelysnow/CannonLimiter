package com.factions.cannonlimiter;

import com.factions.cannonlimiter.listener.DispenserListener;
import com.factions.cannonlimiter.listener.RedstoneListener;
import com.factions.cannonlimiter.registry.ChunkLimiterRegistry;
import com.factions.cannonlimiter.task.ChunkLimiterTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class CannonLimiterPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final ChunkLimiterRegistry chunkLimiterRegistry = new ChunkLimiterRegistry();

        Bukkit.getPluginManager().registerEvents(new DispenserListener(chunkLimiterRegistry), this);
        Bukkit.getPluginManager().registerEvents(new RedstoneListener(chunkLimiterRegistry), this);

        final BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimerAsynchronously(this, new ChunkLimiterTask(chunkLimiterRegistry), 20L, 20L);
    }
}
