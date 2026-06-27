package it.unicam.cs.mpgc.rpg119001.application.service.character;

import it.unicam.cs.mpgc.rpg119001.application.service.combat.CombatService;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.MovementService;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

import java.util.LinkedList;
import java.util.List;

/**
 * Service responsible for executing the player's queued actions during the game loop.
 *
 * <p>This service maintains the current movement path and any pending combat
 * target selected by the player. At each update cycle it progresses the
 * player's movement and performs an attack once the destination has been
 * reached, if applicable.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Store the current movement path.</li>
 *     <li>Store the currently selected combat target.</li>
 *     <li>Execute queued player actions during the game loop.</li>
 *     <li>Coordinate movement and combat execution.</li>
 * </ul>
 */
public class PlayerActionService {

    private List<GridPosition> currentPath = new LinkedList<>();
    private Enemy pendingTarget;

    public void setPath(List<GridPosition> path) {
        this.currentPath = path;
    }

    public void setTarget(Enemy enemy) {
        this.pendingTarget = enemy;
    }

    public void clear() {
        currentPath.clear();
        pendingTarget = null;
    }

    public void update(Game game, MovementService movementService, CombatService combatService) {

        if (currentPath == null || currentPath.isEmpty()) {
            executePendingAttack(game, combatService);
            return;
        }

        moveAlongPath(game, movementService);
    }

    private void executePendingAttack(Game game, CombatService combatService) {

        if (pendingTarget == null) return;

        if (combatService.canAttack(game.getPlayer(), pendingTarget, game.getCurrentRoom())) {
            combatService.attack(game.getPlayer(), pendingTarget, game.getCurrentRoom());
        }

        pendingTarget = null;
    }

    private void moveAlongPath(Game game, MovementService movementService) {

        Player player = game.getPlayer();

        GridPosition next = currentPath.getFirst();

        if (player.getGridPosition().equals(next)) {
            currentPath.removeFirst();
            return;
        }

        movementService.move(player, next, game.getCurrentRoom());
    }
}