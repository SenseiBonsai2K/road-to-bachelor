package it.unicam.cs.mpgc.rpg119001.application.service;

import it.unicam.cs.mpgc.rpg119001.domain.entity.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.domain.world.obstacle.Obstacle;

public class CollisionService {

    public boolean isWalkable(
            GridPosition position,
            Room room) {

        for (Obstacle obstacle : room.getObstacles()) {

            if (obstacle.getGridPosition().equals(position)) {
                return false;
            }
        }

        for (Enemy enemies : room.getEnemies()) {

            if (enemies.getGridPosition().equals(position)) {
                return false;
            }
        }

        return true;
    }
}
