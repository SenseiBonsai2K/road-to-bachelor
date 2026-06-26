package it.unicam.cs.mpgc.rpg119001.application.service.character;

import it.unicam.cs.mpgc.rpg119001.application.service.combat.CombatService;
import it.unicam.cs.mpgc.rpg119001.application.service.combat.LineOfSightService;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.MovementService;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

import java.util.List;
import java.util.Random;

/**
 * Service responsible for controlling the behavior of enemy entities during the game loop.
 *
 * <p>For each enemy, this service evaluates the current game state and determines
 * the appropriate action according to the following priority:</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Call the Combat Service to attack the player if the player is within attack range.</li>
 *     <li>Move towards the player if there is a clear line of sight.</li>
 *     <li>Otherwise, perform a random valid movement.</li>
 * </ul>
 */
public class EnemyAIService {

    private final LineOfSightService lineOfSightService;
    private final CombatService combatService;
    private final MovementService movementService;

    private final Random random = new Random();

    public EnemyAIService(LineOfSightService lineOfSightService,
                          CombatService combatService,
                          MovementService movementService) {

        this.lineOfSightService = lineOfSightService;
        this.combatService = combatService;
        this.movementService = movementService;
    }

    public void updateEnemies(Game game) {
        List<Entity> entities = game.getCurrentRoom().getEntities();
        for (Entity entity : entities) {
            if (entity instanceof Enemy enemy) {
                updateEnemy(enemy, game.getPlayer(), game.getCurrentRoom());
            }
        }
    }

    public void updateEnemy(Enemy enemy, Player player, Room room) {

        GridPosition enemyPos = enemy.getGridPosition();
        GridPosition playerPos = player.getGridPosition();

        if (combatService.canAttack(enemy, player, room)) {
            combatService.attack(enemy, player, room);
            return;
        }

        if (lineOfSightService.hasLineOfSight(enemyPos, playerPos, room)) {
            moveTowardTarget(enemy, playerPos, room);
            return;
        }

        moveRandom(enemy, room);
    }

    private void moveTowardTarget(Enemy enemy, GridPosition targetPosition, Room room) {

        GridPosition current = enemy.getGridPosition();

        List<GridPosition> neighbors = current.getAdjacentPositions();

        GridPosition best = neighbors.stream()
                .filter(neighborPosition -> room.getTileAt(neighborPosition).isWalkable())
                .min((firstPositionToEvaluate, secondPositionToEvaluate) ->
                        Integer.compare(distance(firstPositionToEvaluate, targetPosition), distance(secondPositionToEvaluate, targetPosition))
                )
                .orElse(current);

        if (best.equals(current)) {
            moveRandom(enemy, room);
            return;
        }

        movementService.move(enemy, best, room);
    }

    private void moveRandom(Enemy enemy, Room room) {

        GridPosition current = enemy.getGridPosition();

        List<GridPosition> valid = current.getAdjacentPositions().stream()
                .filter(p -> room.getTileAt(p).isWalkable())
                .toList();

        if (valid.isEmpty()) return;

        GridPosition chosen = valid.get(random.nextInt(valid.size()));

        movementService.move(enemy, chosen, room);
    }

    private int distance(GridPosition positionToEvaluate, GridPosition targetPosition) {
        return Math.abs(positionToEvaluate.getTileX() - targetPosition.getTileX()) +
                Math.abs(positionToEvaluate.getTileY() - targetPosition.getTileY());
    }
}