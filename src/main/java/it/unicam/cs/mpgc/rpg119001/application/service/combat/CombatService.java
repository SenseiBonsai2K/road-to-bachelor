package it.unicam.cs.mpgc.rpg119001.application.service.combat;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.game.Fightable;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

import java.util.function.Consumer;

/**
 * Central service responsible for handling combat interactions Fightable entities.
 *
 * <p>This service coordinates attack validation, damage application,
 * combat cooldowns, death handling and game state updates after combat events.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Validate whether an attack can be performed.</li>
 *     <li>Execute combat interactions between attacker and target.</li>
 *     <li>Manage entity death and removal from the game world.</li>
 *     <li>Trigger external callbacks for UI or game flow updates.</li>
 * </ul>
 *
 * @implNote
 * The damage system is currently simplified and uses only base attack values.
 * Future extensions may include defense, status effects, critical hits and modifiers.
 */
public class CombatService {

    private final RangeService rangeService;
    private final LineOfSightService lineOfSightService;

    private Runnable onPlayerDeath;
    private Runnable onEnemyDeath;
    private Consumer<Enemy> onEnemyDamaged;

    public CombatService(RangeService rangeService, LineOfSightService lineOfSightService) {
        this.rangeService = rangeService;
        this.lineOfSightService = lineOfSightService;
    }

    public boolean canAttack(Fightable attacker, Fightable target, Room room) {
        return hasAttackCooldown(attacker)
                && isInRange(attacker, target)
                && hasLineOfSight(attacker, target, room);
    }

    public void attack(Fightable attacker, Fightable target, Room room) {

        if (!canAttack(attacker, target, room)) {
            return;
        }

        executeAttack(attacker, target, room);
        handlePostAttack(attacker, target, room);
    }

    private void executeAttack(Fightable attacker, Fightable target, Room room) {
        int damage = calculateDamage(attacker);
        target.takeDamage(damage);

        attacker.getCombatState()
                .registerAttack(System.currentTimeMillis());
    }

    private void handlePostAttack(Fightable attacker, Fightable target, Room room) {

        if (target instanceof Enemy enemy && onEnemyDamaged != null) onEnemyDamaged.accept(enemy);
        if (!target.isDead()) return;

        handleDeath(attacker, target, room);
        handleRoomState(room);
    }

    private void handleDeath(Fightable attacker, Fightable target, Room room) {

        room.removeEntity((Entity) target);

        if (target instanceof Enemy enemy) {
            if (attacker instanceof Player player) player.addExperience(enemy.getExperiencePointsReward());
            if (onEnemyDeath != null) onEnemyDeath.run();
        }

        if (target instanceof Player && onPlayerDeath != null) {
            onPlayerDeath.run();
        }
    }

    private void handleRoomState(Room room) {

        boolean enemiesRemaining = room.getEntities().stream()
                .anyMatch(e -> e instanceof Enemy);

        if (!enemiesRemaining) room.destroyExitDoor();
    }

    private boolean hasAttackCooldown(Fightable attacker) {
        return attacker.getCombatState()
                .canAttack(System.currentTimeMillis(), attacker.getAttackSpeed());
    }

    private boolean isInRange(Fightable attacker, Fightable target) {
        GridPosition attackerPosition = attacker.getGridPosition();
        GridPosition targetPosition = target.getGridPosition();

        return rangeService.isInRange(attackerPosition, targetPosition, attacker.getAttackRange());
    }

    private boolean hasLineOfSight(Fightable attacker, Fightable target, Room room) {
        return lineOfSightService.hasLineOfSight(
                attacker.getGridPosition(),
                target.getGridPosition(),
                room
        );
    }

    private int calculateDamage(Fightable attacker) {
        return attacker.getAttackPoints();
    }

    public void setOnPlayerDeath(Runnable onPlayerDeath) {
        this.onPlayerDeath = onPlayerDeath;
    }

    public void setOnEnemyDeath(Runnable onEnemyDeath) {
        this.onEnemyDeath = onEnemyDeath;
    }

    public void setOnEnemyDamaged(Consumer<Enemy> onEnemyDamaged) {
        this.onEnemyDamaged = onEnemyDamaged;
    }
}