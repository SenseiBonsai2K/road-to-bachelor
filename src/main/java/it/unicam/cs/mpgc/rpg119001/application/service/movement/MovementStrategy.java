package it.unicam.cs.mpgc.rpg119001.application.service.movement;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

public interface MovementStrategy {
    GridPosition nextStep(GridPosition current, GridPosition target);
}
