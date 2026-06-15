package it.unicam.cs.mpgc.rpg119001.infrastructure.factory;

import it.unicam.cs.mpgc.rpg119001.config.Constants.GameConstants;
import it.unicam.cs.mpgc.rpg119001.config.Constants.GridConstants;
import it.unicam.cs.mpgc.rpg119001.domain.entity.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.entity.Tile;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomFactory {

    private final EnemyFactory enemyFactory;
    private static final Random random = new Random();

    public RoomFactory(EnemyFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
    }

    public Room createRoom(int level) {

        Tile[][] tiles = TileFactory.createBaseRoom();

        List<Entity> entities = new ArrayList<>();

        boolean[][] occupied = new boolean[GridConstants.ROOM_TILES_WIDTH][GridConstants.ROOM_TILES_HEIGHT];

        int enemyCount = 1 + random.nextInt(GameConstants.DIFFICULTY + level / 2);

        //TODO manage Spawn Position by using level

        GridPosition spawnPlayerPosition = new GridPosition(
                GridConstants.WALL_THICKNESS_TILES,
                GridConstants.ROOM_TILES_HEIGHT / 2
        );

        for (int i = 0; i < enemyCount; i++) {

            Enemy enemy = enemyFactory.createRandomEnemies(level);

            GridPosition pos = randomValidPosition(occupied, tiles, spawnPlayerPosition);

            enemy.setGridPosition(pos);

            entities.add(enemy);

            occupied[pos.getTileX()][pos.getTileY()] = true;
        }

        return new Room(tiles, entities, spawnPlayerPosition);
    }

    private GridPosition randomValidPosition(boolean[][] occupied, Tile[][] tiles, GridPosition spawnPlayerPosition) {
        for (int i = 0; i < 50; i++) {

            int x = 1 + random.nextInt(GridConstants.ROOM_TILES_WIDTH - 2);
            int y = 1 + random.nextInt(GridConstants.ROOM_TILES_HEIGHT - 2);

            GridPosition position = new GridPosition(x, y);
            if (!occupied[x][y] && tiles[x][y].isWalkable() && !spawnPlayerPosition.equals(position)) {
                return position;
            }
        }

        throw new IllegalStateException("Cannot find valid spawn position");
    }
}