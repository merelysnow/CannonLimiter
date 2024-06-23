package com.merelysnow.cannonlimiter;

import com.merelysnow.cannonlimiter.cache.ChunkCache;
import com.merelysnow.cannonlimiter.commands.ChunkLimiterCommand;
import com.merelysnow.cannonlimiter.commands.subcommand.ChunkLimiterListCommand;
import com.merelysnow.cannonlimiter.commands.subcommand.ChunkTPChunkCommand;
import com.merelysnow.cannonlimiter.listeners.DispenserListener;
import com.merelysnow.cannonlimiter.listeners.RedstoneListener;
import com.merelysnow.cannonlimiter.tasks.ChunkLimiterTask;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.command.message.MessageHolder;
import me.saiintbrisson.minecraft.command.message.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class CannonLimiterPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        ChunkCache chunkCache = new ChunkCache();

        registerCommands(chunkCache);

        Bukkit.getPluginManager().registerEvents(new DispenserListener(chunkCache), this);
        Bukkit.getPluginManager().registerEvents(new RedstoneListener(chunkCache), this);

        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimerAsynchronously(this, new ChunkLimiterTask(chunkCache), 20L, 20L);
    }

    private void registerCommands(ChunkCache chunkCache) {
        final BukkitFrame bukkitFrame = new BukkitFrame(this);

        bukkitFrame.registerCommands(
                new ChunkLimiterCommand(),
                new ChunkLimiterListCommand(chunkCache),
                new ChunkTPChunkCommand()
        );

        MessageHolder messageHolder = bukkitFrame.getMessageHolder();

        messageHolder.setMessage(MessageType.NO_PERMISSION, "§cVocê não tem permissão para executar este comando.");
        messageHolder.setMessage(MessageType.ERROR, "§cUm erro ocorreu! {error}");
        messageHolder.setMessage(MessageType.INCORRECT_USAGE, "§cUtilize /{usage}");
        messageHolder.setMessage(MessageType.INCORRECT_TARGET, "§cVocê não pode utilizar este comando pois ele é direcioado apenas para {target}.");
    }
}
