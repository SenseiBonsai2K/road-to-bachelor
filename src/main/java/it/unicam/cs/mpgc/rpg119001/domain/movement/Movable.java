package it.unicam.cs.mpgc.rpg119001.domain.movement;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

public interface Movable {

    GridPosition getGridPosition();
    void setGridPosition(GridPosition position);

    MovementState getMovementState();
    int getSpeed();
}
