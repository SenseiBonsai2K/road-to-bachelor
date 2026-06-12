package it.unicam.cs.mpgc.rpg119001.game.world;

import it.unicam.cs.mpgc.rpg119001.game.entity.Enemy;
import it.unicam.cs.mpgc.rpg119001.game.world.obstacle.Obstacle;

import java.util.List;

public class Room {
    private final List<Enemy> enemies;
    private final List<Obstacle> obstacles;
    private final GridPosition playerSpawnPosition;

    public Room(List<Enemy> enemies,
                List<Obstacle> obstacles,
                GridPosition playerSpawnPosition) {
        this.enemies = enemies;
        this.obstacles = obstacles;
        this.playerSpawnPosition = playerSpawnPosition;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public GridPosition getPlayerSpawnPosition() {
        return playerSpawnPosition;
    }
}
