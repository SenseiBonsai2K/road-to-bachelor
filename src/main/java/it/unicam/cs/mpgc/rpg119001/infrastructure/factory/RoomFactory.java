package it.unicam.cs.mpgc.rpg119001.infrastructure.factory;

import it.unicam.cs.mpgc.rpg119001.config.Constants.GameConstants;
import it.unicam.cs.mpgc.rpg119001.config.Constants.GridConstants;
import it.unicam.cs.mpgc.rpg119001.domain.entity.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.domain.world.obstacle.Obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomFactory {

    private final EnemyFactory enemyFactory;
    private static final Random random = new Random();

    public RoomFactory(EnemyFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
    }

    public static Room createRoom(int level) {

        List<Enemy> enemies = new RoomFactory(new EnemyFactory()).createEnemies(level);
        List<Obstacle> obstacles = new ArrayList<>(ObstacleFactory.createBoundaryWalls());
        //TODO spawn door and other obstacles when implemented
        // Spawn player at left side, middle height
        GridPosition spawn = new GridPosition(
            GridConstants.WALL_THICKNESS_TILES,
            GridConstants.ROOM_TILES_HEIGHT / 2
        );

        return new Room(enemies, obstacles, spawn);
    }

    private List<Enemy> createEnemies(int level) {

        List<Enemy> enemies = new ArrayList<>();
        int enemyCount = 1 + random.nextInt(GameConstants.DIFFICULTY + level / 2);

        for (int i = 0; i < enemyCount; i++) {
            Enemy enemy = enemyFactory.createRandomEnemies(level);
            enemy.setGridPosition(randomGridPosition());
            enemies.add(enemy);
        }

        return enemies;
    }

    private GridPosition randomGridPosition() {
        int tileX = GridConstants.WALL_THICKNESS_TILES + random.nextInt(GridConstants.ROOM_TILES_WIDTH - 2 * GridConstants.WALL_THICKNESS_TILES);
        int tileY = GridConstants.WALL_THICKNESS_TILES + random.nextInt(GridConstants.ROOM_TILES_HEIGHT - 2 * GridConstants.WALL_THICKNESS_TILES);

        return new GridPosition(tileX, tileY);
    }
}
