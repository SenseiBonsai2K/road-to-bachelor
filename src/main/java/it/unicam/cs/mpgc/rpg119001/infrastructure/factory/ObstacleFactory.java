package it.unicam.cs.mpgc.rpg119001.infrastructure.factory;

import it.unicam.cs.mpgc.rpg119001.config.Constants.GridConstants;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.obstacle.Wall;

import java.util.ArrayList;
import java.util.List;

public class ObstacleFactory {

    public static List<Wall> createBoundaryWalls() {
        List<Wall> walls = new ArrayList<>();

        // Top wall
        for (int tile = 1; tile < GridConstants.ROOM_TILES_WIDTH; tile++) {
            walls.add(new Wall(new GridPosition(tile, 0)));
        }

        // Bottom wall
        for (int tile = 1; tile < GridConstants.ROOM_TILES_WIDTH; tile++) {
            walls.add(new Wall(new GridPosition(tile, GridConstants.ROOM_TILES_HEIGHT)));
        }

        // Left wall
        for (int tile = 0; tile <= GridConstants.ROOM_TILES_HEIGHT; tile++) {
            walls.add(new Wall(new GridPosition(0, tile)));
        }

        // Right wall
        for (int tile = 0; tile <= GridConstants.ROOM_TILES_HEIGHT; tile++) {
            walls.add(new Wall(new GridPosition(GridConstants.ROOM_TILES_WIDTH, tile)));
        }

        return walls;
    }
}

