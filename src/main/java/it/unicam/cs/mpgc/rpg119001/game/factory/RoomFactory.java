package it.unicam.cs.mpgc.rpg119001.game.factory;

import it.unicam.cs.mpgc.rpg119001.game.config.Constants.GameConstants;
import it.unicam.cs.mpgc.rpg119001.game.config.Constants.RoomConstants;
import it.unicam.cs.mpgc.rpg119001.game.entity.Enemy;
import it.unicam.cs.mpgc.rpg119001.game.world.Position;
import it.unicam.cs.mpgc.rpg119001.game.world.Room;
import it.unicam.cs.mpgc.rpg119001.game.world.obstacle.Obstacle;

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

        //TODO change with door position when implemented
        Position spawn = new Position(2, RoomConstants.ROOM_HEIGHT/2);

        return new Room(enemies, obstacles, spawn);
    }

    private List<Enemy> createEnemies(int level) {

        List<Enemy> enemies = new ArrayList<>();
        int enemyCount = 1 + random.nextInt(GameConstants.DIFFICULTY + level / 2);

        for (int i = 0; i < enemyCount; i++) {
            Enemy enemy = enemyFactory.createRandomEnemies(level);
            enemy.setPosition(randomPosition());
            enemies.add(enemy);
        }

        return enemies;
    }

    private Position randomPosition() {

        int x = random.nextInt(RoomConstants.ROOM_WIDTH);
        int y = random.nextInt(RoomConstants.ROOM_HEIGHT);

        return new Position(x, y);
    }
}
