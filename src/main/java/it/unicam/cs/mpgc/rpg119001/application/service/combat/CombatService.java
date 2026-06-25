package it.unicam.cs.mpgc.rpg119001.application.service.combat;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.game.Fightable;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

import java.util.function.Consumer;

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
        GridPosition attackerGridPosition = attacker.getGridPosition();
        GridPosition targetGridPosition = target.getGridPosition();

        if (!attacker.getCombatState().canAttack(System.currentTimeMillis(), attacker.getAttackSpeed())) return false;

        return rangeService.isInRange(attackerGridPosition, targetGridPosition, attacker.getAttackRange()) &&
                lineOfSightService.hasLineOfSight(attackerGridPosition, targetGridPosition, room);
    }

    public void attack(Fightable attacker,
                       Fightable target,
                       Room room) {

        if (!canAttack(attacker, target, room)) {
            return;
        }

        int damage = calculateDamage(attacker);

        target.takeDamage(damage);

        attacker.getCombatState()
                .registerAttack(System.currentTimeMillis());

        if (target instanceof Enemy enemy && onEnemyDamaged != null) {
            onEnemyDamaged.accept(enemy);
        }

        if (!target.isDead()) {
            return;
        }

        room.removeEntity((Entity) target);

        if (target instanceof Enemy enemy) {
            if (onEnemyDeath != null) {
                onEnemyDeath.run();
            }

            if (attacker instanceof Player player) {
                player.addExperience(enemy.getExperiencePointsReward());
            }
        }

        if (target instanceof Player player) {
            if (onPlayerDeath != null) {
                onPlayerDeath.run();
            }
        }

        boolean enemiesRemaining = room.getEntities().stream()
                .anyMatch(entity -> entity instanceof Enemy);

        if (!enemiesRemaining) {
            room.destroyExitDoor();
        }
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

    private int calculateDamage(Fightable attacker) {
        int totalDamage;

        int baseDamage = attacker.getAttackPoints();
        //TODO Implement defense, effect ecc.. to add to damage

        totalDamage = baseDamage;
        return totalDamage;
    }
}