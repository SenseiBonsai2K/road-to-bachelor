package it.unicam.cs.mpgc.rpg119001.game;

import it.unicam.cs.mpgc.rpg119001.game.world.GridPosition;

public interface Movable {

    GridPosition getGridPosition();
    void setGridPosition(GridPosition position);

    MovementState getMovementState();
    int getSpeed();
}
