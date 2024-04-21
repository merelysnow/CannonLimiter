package com.factions.cannonlimiter.command.subcommand;

import com.factions.cannonlimiter.chunk.ChunkLimiter;
import com.factions.cannonlimiter.registry.ChunkLimiterRegistry;
import com.factions.cannonlimiter.util.ChunkCoordinates;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class ChunkLimiterListCommand {

    private final ChunkLimiterRegistry chunkLimiterRegistry;

    @Command(
            name = "chunklimiter.list",
            permission = "chunklimiter.admin",
            target = CommandTarget.PLAYER
    )
    public void handleCommand(Context<Player> context) {
        final Player player = context.getSender();

        final StringBuilder stringBuilder = new StringBuilder("\n ");
        stringBuilder.append(" §c§lCHUNKLIMITER - CHUNKS")
                .append("\n ")
                .append("\n ");

        for (ChunkLimiter chunkLimiter : chunkLimiterRegistry.getValues()) {
            final ChunkCoordinates chunkCoordinates = chunkLimiter.getChunkCoordinates();

            stringBuilder.append(String.format("§c§l[%s, %s] §fTicks: §7%s §fDispenser: §7%s",
                            chunkCoordinates.getX(), chunkCoordinates.getZ(), chunkLimiter.getIntervalTicks(), chunkLimiter.getDispenserAmount()))
                    .append("\n");
        }

        player.sendMessage(stringBuilder.toString());
    }
}
