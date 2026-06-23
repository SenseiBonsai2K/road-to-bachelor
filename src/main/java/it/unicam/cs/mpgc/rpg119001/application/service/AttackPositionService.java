package it.unicam.cs.mpgc.rpg119001.application.service;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Character;

import java.util.ArrayList;
import java.util.List;

public class AttackPositionService {

    private final RangeService rangeService;
    private final LineOfSightService lineOfSightService;
    private final PathfindingService pathfindingService;
    private final CollisionService collisionService;

    public AttackPositionService(RangeService rangeService,
                                 LineOfSightService lineOfSightService,
                                 PathfindingService pathfindingService,
                                 CollisionService collisionService) {
        this.rangeService = rangeService;
        this.lineOfSightService = lineOfSightService;
        this.pathfindingService = pathfindingService;
        this.collisionService = collisionService;
    }

    public GridPosition findBestAttackPosition(Character attacker, Character target, Room room) {

        if (rangeService.isInRange(attacker.getGridPosition(), target.getGridPosition(), attacker.getAttackRange())
                && lineOfSightService.hasLineOfSight(attacker.getGridPosition(), target.getGridPosition(), room)) {
            return attacker.getGridPosition();
        }

        List<GridPosition> candidates = generateCandidateTiles(target.getGridPosition(), attacker.getAttackRange());

        candidates.removeIf(candidate -> !this.collisionService.isInside(room, candidate));

        GridPosition bestCandidate = null;
        int bestCost = Integer.MAX_VALUE;

        for (GridPosition candidate : candidates) {

            if (!rangeService.isInRange(candidate, target.getGridPosition(), attacker.getAttackRange())) {
                continue;
            }

            if (!lineOfSightService.hasLineOfSight(candidate, target.getGridPosition(), room)) {
                continue;
            }

            List<GridPosition> path = pathfindingService.findPath(
                    attacker.getGridPosition(),
                    candidate,
                    room,
                    collisionService
            );

            if (path.isEmpty()) {
                continue;
            }

            int cost = path.size();

            if (cost < bestCost) {
                bestCost = cost;
                bestCandidate = candidate;
            }
        }

        return bestCandidate;
    }

    private List<GridPosition> generateCandidateTiles(GridPosition targetPos, int range) {

        List<GridPosition> result = new ArrayList<>();

        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {

                GridPosition candidate = new GridPosition(
                        targetPos.getTileX() + x,
                        targetPos.getTileY() + y
                );
                result.add(candidate);
            }
        }

        return result;
    }
}
