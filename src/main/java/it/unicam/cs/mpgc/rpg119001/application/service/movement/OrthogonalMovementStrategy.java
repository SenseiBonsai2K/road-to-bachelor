package it.unicam.cs.mpgc.rpg119001.application.service.movement;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

public class OrthogonalMovementStrategy implements MovementStrategy{

    @Override
    public GridPosition nextStep(GridPosition current, GridPosition target) {
        int dx = Integer.compare(target.getTileX(), current.getTileX());
        int dy = Integer.compare(target.getTileY(), current.getTileY());

        if (dx != 0 && dy != 0) dy = 0;

        return current.translate(dx, dy);
    }
}
