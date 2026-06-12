package it.unicam.cs.mpgc.rpg119001.game.world.obstacle;

import it.unicam.cs.mpgc.rpg119001.game.config.Constants.ObstacleImagePathConstants;
import it.unicam.cs.mpgc.rpg119001.game.entity.Player;
import it.unicam.cs.mpgc.rpg119001.game.entity.SpriteEntity;
import it.unicam.cs.mpgc.rpg119001.game.world.GridPosition;

public class Wall implements Obstacle, SpriteEntity {

    private final GridPosition gridPosition;

    public Wall(GridPosition gridPosition) {
        this.gridPosition = gridPosition;
    }

    @Override
    public GridPosition getGridPosition() {
        return this.gridPosition;
    }

    @Override
    public String getImagePath() {
        return ObstacleImagePathConstants.WALL;
    }

    @Override
    public void interact(Player player) {
    }

    @Override
    public boolean isPassable() {
        return false;
    }
}
