package it.unicam.cs.mpgc.rpg119001.infrastructure.room;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Tile;

import java.util.List;

public record RoomTemplate(
        Tile[][] tiles,
        GridPosition playerSpawn,
        List<GridPosition> enemySpawns,
        GridPosition entranceSpawn,
        GridPosition leaveSpawn
) {
}