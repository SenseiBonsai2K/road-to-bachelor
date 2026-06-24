package it.unicam.cs.mpgc.rpg119001.application.service;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Character;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.game.Fightable;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

import java.util.concurrent.ForkJoinPool;

public class CombatService {

    private final RangeService rangeService;
    private final LineOfSightService lineOfSightService;

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

    public void attack(Character attacker, Character target, Room room) {

        if (!canAttack(attacker, target, room)) return;

        int damage = calculateDamage(attacker);

        target.takeDamage(damage);

        attacker.getCombatState().registerAttack(System.currentTimeMillis());

        if (target.isDead()) {
            room.removeEntity(target);
            if (attacker instanceof Player player && target instanceof Enemy enemy) {
                player.addExperience(enemy.getExperiencePointsReward());
            }
            for (Entity entity : room.getEntities()) {
                if (entity instanceof Enemy) return;
            }
            room.destroyExitDoor();
        }
    }

    private int calculateDamage(Character attacker) {
        int damage;

        int base = attacker.getAttackPoints();
        //TODO Implement defense, effect ecc.. to add to damage

        damage = base;
        return damage;
    }
}