package it.unicam.cs.mpgc.rpg119001.application.service.movement;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

/**
 * Movement strategy that restricts movement to the four orthogonal
 * directions of the grid.
 *
 * <p>Diagonal movement is not allowed. When both horizontal and vertical
 * movement would be required, horizontal movement is prioritized.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Compute the next orthogonal movement step.</li>
 *     <li>Prevent diagonal movement.</li>
 * </ul>
 */
public class OrthogonalMovementStrategy implements MovementStrategy{

    @Override
    public GridPosition nextStep(GridPosition current, GridPosition target) {
        int dx = Integer.compare(target.getTileX(), current.getTileX());
        int dy = Integer.compare(target.getTileY(), current.getTileY());

        if (dx != 0 && dy != 0) dy = 0;

        return current.translate(dx, dy);
    }
}
