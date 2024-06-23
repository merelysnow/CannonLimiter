package com.merelysnow.cannonlimiter.commands.subcommand;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ChunkTPChunkCommand {

    @Command(
            name = "chunklimiter.tpchunk",
            permission = "chunklimiter.admin",
            target = CommandTarget.PLAYER
    )
    public void handleCommand(Context<Player> context, int x, int z) {
        final Player player = context.getSender();

        int xmin = x * 16;
        int xmax = xmin + 15;
        int zmin = z * 16;
        int zmax = zmin + 15;

        final int correctX = (xmin + xmax) / 2;
        final int correctZ = (zmin + zmax) / 2;

        player.teleport(new Location(player.getWorld(), correctX, 90, correctZ));
    }
}
