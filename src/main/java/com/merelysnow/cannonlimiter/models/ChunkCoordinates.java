package com.merelysnow.cannonlimiter.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ChunkCoordinates {
    private final double x;
    private final double z;
    private final String worldName;
}
