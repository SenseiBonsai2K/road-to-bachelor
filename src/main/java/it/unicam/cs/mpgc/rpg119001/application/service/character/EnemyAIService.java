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
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.EnemyPresets;

import java.util.List;
import java.util.Random;

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
            moveTowardEnemy(enemy, playerPos, room);
            return;
        }

        moveRandom(enemy, room);
    }

    private void moveTowardEnemy(Enemy enemy, GridPosition target, Room room) {

        GridPosition current = enemy.getGridPosition();

        List<GridPosition> neighbors = current.getAdjacentPositions();

        GridPosition best = neighbors.stream()
                .filter(p -> room.getTileAt(p).isWalkable())
                .min((a, b) ->
                        Integer.compare(distance(a, target), distance(b, target))
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

    private int distance(GridPosition a, GridPosition b) {
        return Math.abs(a.getTileX() - b.getTileX())
                + Math.abs(a.getTileY() - b.getTileY());
    }
}