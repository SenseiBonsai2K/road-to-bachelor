package it.unicam.cs.mpgc.rpg119001.model;

import it.unicam.cs.mpgc.rpg119001.util.Constants.EnemyConstants;

/**
 * The Enemy class represents the adversaries that the player will encounter in the RPG game.
 * It extends the Character class and includes specific attributes and behaviors for enemies.
 */
public class Enemy extends Character {

    public Enemy(String name, int healthPoints, int attackPoints, Position position) {
        validateEnemyName(name);
        super(
                name,
                validateEnemyStat(healthPoints, EnemyConstants.HEALTH_POINTS),
                validateEnemyStat(attackPoints, EnemyConstants.ATTACK_POINTS),
                position
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
