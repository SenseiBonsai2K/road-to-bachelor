package it.unicam.cs.mpgc.rpg119001.application.service.character;

import it.unicam.cs.mpgc.rpg119001.application.service.combat.CombatService;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.MovementService;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

import java.util.LinkedList;
import java.util.List;

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

    public void update(Game game,
                       MovementService movementService,
                       CombatService combatService) {

        Player player = game.getPlayer();
        Room room = game.getCurrentRoom();

        if (currentPath == null || currentPath.isEmpty()) {

            if (pendingTarget != null) {
                if (combatService.canAttack(game.getPlayer(), pendingTarget, game.getCurrentRoom())) {
                    combatService.attack(game.getPlayer(), pendingTarget, game.getCurrentRoom());
                }

                pendingTarget = null;
            }

            return;
        }

        GridPosition next = currentPath.getFirst();

        if (player.getGridPosition().equals(next)) {
            currentPath.removeFirst();
            return;
        }

        movementService.move(player, next, room);
    }
}