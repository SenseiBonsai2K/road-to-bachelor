package it.unicam.cs.mpgc.rpg119001.domain.movement;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

/**
 * Defines the contract for entities capable of moving within the game world.
 *
 * <p>Implementations expose their current position, movement state and
 * movement speed</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Expose the current grid position.</li>
 *     <li>Allow updating the entity position.</li>
 *     <li>Provide access to movement timing information.</li>
 *     <li>Expose the movement speed.</li>
 * </ul>
 */
public interface Movable {

    GridPosition getGridPosition();
    void setGridPosition(GridPosition position);

    MovementState getMovementState();
    int getSpeed();
}
