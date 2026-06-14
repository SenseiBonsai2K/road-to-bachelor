package it.unicam.cs.mpgc.rpg119001.domain.world.obstacle;

import it.unicam.cs.mpgc.rpg119001.domain.entity.Player;
import it.unicam.cs.mpgc.rpg119001.domain.entity.SpriteEntity;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

public interface Obstacle extends SpriteEntity {

    GridPosition getGridPosition();

    void interact(Player player);

    boolean isPassable();
}
