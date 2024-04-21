package com.factions.cannonlimiter.command;

import com.factions.cannonlimiter.registry.ChunkLimiterRegistry;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

public class ChunkLimiterCommand {

    @Command(
            name = "chunklimiter",
            permission = "chunklimiter.admin",
            target = CommandTarget.PLAYER
    )
    public void handleCommand(Context<Player> context) {
        final Player player = context.getSender();

        player.sendMessage(new String[]{"",
                " §c§lCHUNKLIMITER - COMANDOS",
                "",
                " §f/chunklimiter list - §7Veja todas as chunks",
                ""});
    }
}
