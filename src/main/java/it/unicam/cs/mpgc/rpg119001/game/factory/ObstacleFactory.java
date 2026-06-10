package it.unicam.cs.mpgc.rpg119001.game.factory;

import it.unicam.cs.mpgc.rpg119001.game.config.Constants.RoomConstants;
import it.unicam.cs.mpgc.rpg119001.game.world.Position;
import it.unicam.cs.mpgc.rpg119001.game.world.obstacle.Wall;

import java.util.ArrayList;
import java.util.List;

public class ObstacleFactory {

    private static final int WALL_THICKNESS = 10;

    public static List<Wall> createBoundaryWalls() {
        List<Wall> walls = new ArrayList<>();

        walls.add(new Wall(new Position(0, 0), RoomConstants.ROOM_WIDTH, WALL_THICKNESS));
        walls.add(new Wall(new Position(0, RoomConstants.ROOM_HEIGHT - WALL_THICKNESS), RoomConstants.ROOM_WIDTH, WALL_THICKNESS));
        walls.add(new Wall(new Position(0, 0), WALL_THICKNESS, RoomConstants.ROOM_HEIGHT));
        walls.add(new Wall(new Position(RoomConstants.ROOM_WIDTH - WALL_THICKNESS, 0), WALL_THICKNESS, RoomConstants.ROOM_HEIGHT));

        return walls;
    }
}

