package it.unicam.cs.mpgc.rpg119001.application.service.combat;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

/**
 * Service responsible for calculating attack range and distance evaluation between positions.
 *
 * <p>This service provides utility methods for determining whether a target is within
 * attack range using Manhattan distance.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Check if a target is within a given range.</li>
 *     <li>Compute Manhattan distance between two grid positions.</li>
 * </ul>
 */
public class RangeService {

    public boolean isInRange(GridPosition candidatePosition, GridPosition targetPosition, int range) {
        return calculateDistance(candidatePosition, targetPosition) <= range;
    }

    public int calculateDistance(GridPosition candidatePosition, GridPosition targetPosition) {
        return Math.abs(candidatePosition.getTileX() - targetPosition.getTileX()) +
                Math.abs(candidatePosition.getTileY() - targetPosition.getTileY());
    }
}