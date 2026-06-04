package it.unicam.cs.mpgc.rpg119001.model;

import it.unicam.cs.mpgc.rpg119001.util.CharacterConstants.EnemyConstants;

public class Enemy extends Character {

    public Enemy(String name, int healthPoints, int attackPoints) {
        validateEnemyName(name);
        super(
                name,
                validateEnemyStat(healthPoints, EnemyConstants.HEALTH_POINTS),
                validateEnemyStat(attackPoints, EnemyConstants.ATTACK_POINTS)
        );
    }

    private static void validateEnemyName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Enemy name cannot be null or empty.");
        }
    }

    private static int validateEnemyStat(int stat, int defaultValue) {
        return (stat > 0) ? stat : defaultValue;
    }

}
