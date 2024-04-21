package com.factions.cannonlimiter;

import com.factions.cannonlimiter.command.ChunkLimiterCommand;
import com.factions.cannonlimiter.command.subcommand.ChunkLimiterListCommand;
import com.factions.cannonlimiter.command.subcommand.ChunkTPChunkCommand;
import com.factions.cannonlimiter.listener.DispenserListener;
import com.factions.cannonlimiter.listener.RedstoneListener;
import com.factions.cannonlimiter.registry.ChunkLimiterRegistry;
import com.factions.cannonlimiter.task.ChunkLimiterTask;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.command.message.MessageHolder;
import me.saiintbrisson.minecraft.command.message.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class CannonLimiterPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final ChunkLimiterRegistry chunkLimiterRegistry = new ChunkLimiterRegistry();

        registerCommands(chunkLimiterRegistry);

        Bukkit.getPluginManager().registerEvents(new DispenserListener(chunkLimiterRegistry), this);
        Bukkit.getPluginManager().registerEvents(new RedstoneListener(chunkLimiterRegistry), this);

        final BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimerAsynchronously(this, new ChunkLimiterTask(chunkLimiterRegistry), 20L, 20L);
    }

    private void registerCommands(ChunkLimiterRegistry chunkLimiterRegistry) {
        final BukkitFrame bukkitFrame = new BukkitFrame(this);

        bukkitFrame.registerCommands(
                new ChunkLimiterCommand(),
                new ChunkLimiterListCommand(chunkLimiterRegistry),
                new ChunkTPChunkCommand()
        );

        final MessageHolder messageHolder = bukkitFrame.getMessageHolder();

        messageHolder.setMessage(MessageType.NO_PERMISSION, "§cVocê não tem permissão para executar este comando.");
        messageHolder.setMessage(MessageType.ERROR, "§cUm erro ocorreu! {error}");
        messageHolder.setMessage(MessageType.INCORRECT_USAGE, "§cUtilize /{usage}");
        messageHolder.setMessage(MessageType.INCORRECT_TARGET, "§cVocê não pode utilizar este comando pois ele é direcioado apenas para {target}.");
    }
}
