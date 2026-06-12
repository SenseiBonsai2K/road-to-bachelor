package it.unicam.cs.mpgc.rpg119001.game.world.obstacle;

import it.unicam.cs.mpgc.rpg119001.game.entity.Player;
import it.unicam.cs.mpgc.rpg119001.game.entity.SpriteEntity;
import it.unicam.cs.mpgc.rpg119001.game.world.GridPosition;

public interface Obstacle extends SpriteEntity {

    GridPosition getGridPosition();

    void interact(Player player);

    boolean isPassable();
}
