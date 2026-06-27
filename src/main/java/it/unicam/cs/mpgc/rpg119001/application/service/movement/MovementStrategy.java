package it.unicam.cs.mpgc.rpg119001.application.service.movement;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

/**
 * Defines the contract for movement algorithms used by movable entities.
 *
 * <p>Different implementations may provide alternative movement behaviors,
 * such as orthogonal, diagonal or custom movement rules.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Compute the next movement step towards a target position.</li>
 * </ul>
 */
public interface MovementStrategy {
    GridPosition nextStep(GridPosition current, GridPosition target);
}
