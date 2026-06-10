package it.unicam.cs.mpgc.rpg119001.game.world;

import it.unicam.cs.mpgc.rpg119001.game.entity.Enemy;
import it.unicam.cs.mpgc.rpg119001.game.world.obstacle.Obstacle;

import java.util.List;

public class Room {
    private final List<Enemy> enemies;
    private final List<Obstacle> obstacles;
    private final Position spawnPosition;

    public Room(List<Enemy> enemies,
                List<Obstacle> obstacles,
                Position spawnPosition) {
        this.enemies = enemies;
        this.obstacles = obstacles;
        this.spawnPosition = spawnPosition;
    }

    public boolean isCompleted() {
        return enemies.isEmpty();
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public Position getSpawnPosition() {
        return spawnPosition;
    }
}
