package com.factions.cannonlimiter.util;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bukkit.Chunk;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ChunkCoordinates {

    private double x, z;
}
