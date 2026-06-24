package it.unicam.cs.mpgc.rpg119001.application.service.combat;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

public class RangeService {

    public boolean isInRange(GridPosition candidateTile,
                             GridPosition targetPos,
                             int range) {

        return calculateDistance(candidateTile, targetPos) <= range;
    }

    public int calculateDistance(GridPosition a,
                                 GridPosition b) {

        return Math.abs(a.getTileX() - b.getTileX())
                + Math.abs(a.getTileY() - b.getTileY());
    }
}