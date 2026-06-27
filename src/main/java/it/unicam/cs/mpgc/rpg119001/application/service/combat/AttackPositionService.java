package it.unicam.cs.mpgc.rpg119001.application.service.combat;

import it.unicam.cs.mpgc.rpg119001.application.service.game.CollisionService;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.PathfindingService;
import it.unicam.cs.mpgc.rpg119001.domain.game.Fightable;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Service responsible for finding optimal attack positions for Fightable entity.
 *
 * <p>This service is used mainly for AI behavior. It computes the best reachable
 * tile from which an attacker can engage a target while respecting range and
 * visibility constraints.</p>
 *
 * <p>If the attacker is already in a valid attack position, that position is returned.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Evaluate whether the current position is already optimal.</li>
 *     <li>Generate candidate positions around the target.</li>
 *     <li>Filter valid attack positions based on range and visibility.</li>
 *     <li>Select the best position based on movement cost (path length).</li>
 * </ul>
 */
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

    public GridPosition findBestAttackPosition(Fightable attacker, Fightable target, Room room) {

        if (isValidAttackPosition(attacker, target, room)) {
            return attacker.getGridPosition();
        }

        return findBestCandidatePosition(attacker, target, room);
    }

    private boolean isValidAttackPosition(Fightable attacker, Fightable target, Room room) {
        return rangeService.isInRange(attacker.getGridPosition(), target.getGridPosition(), attacker.getAttackRange())
                && lineOfSightService.hasLineOfSight(attacker.getGridPosition(), target.getGridPosition(), room);
    }

    private GridPosition findBestCandidatePosition(Fightable attacker, Fightable target, Room room) {

        List<GridPosition> candidates = generateCandidateTiles(
                target.getGridPosition(),
                attacker.getAttackRange()
        );

        candidates.removeIf(candidate -> !collisionService.isInside(room, candidate));

        return candidates.stream()
                .filter(candidate -> isValidAttackPosition(attacker, target, room, candidate))
                .filter(candidate -> !pathfindingService.findPath(attacker.getGridPosition(), candidate, room).isEmpty())
                .min(Comparator.comparingInt(candidate -> computeMovementCost(attacker, candidate, room)))
                .orElse(null);
    }

    private boolean isValidAttackPosition(Fightable attacker, Fightable target, Room room, GridPosition candidate) {
        return rangeService.isInRange(candidate, target.getGridPosition(), attacker.getAttackRange())
                && lineOfSightService.hasLineOfSight(candidate, target.getGridPosition(), room);
    }

    private int computeMovementCost(Fightable attacker, GridPosition candidate, Room room) {
        return pathfindingService
                .findPath(attacker.getGridPosition(), candidate, room)
                .size();
    }

    private List<GridPosition> generateCandidateTiles(GridPosition targetPosition, int range) {

        List<GridPosition> result = new ArrayList<>();

        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {

                GridPosition candidate = new GridPosition(
                        targetPosition.getTileX() + x,
                        targetPosition.getTileY() + y
                );
                result.add(candidate);
            }
        }

        return result;
    }
}
