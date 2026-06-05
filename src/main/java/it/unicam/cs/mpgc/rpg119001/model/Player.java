package it.unicam.cs.mpgc.rpg119001.model;

import it.unicam.cs.mpgc.rpg119001.util.Constants.PlayerConstants;

/**
 * The Player class represents the main character controlled by the user in the RPG game.
 * It extends the Character class and includes specific attributes and behaviors for the player.
 */
public class Player extends Character {

    public Player(String name, int healthPoints, int attackPoints, Position position) {
        super(
                validatePlayerName(name),
                validatePlayerStat(healthPoints, PlayerConstants.HEALTH_POINTS),
                validatePlayerStat(attackPoints, PlayerConstants.ATTACK_POINTS),
                position
        );
    }

    private static String validatePlayerName(String name) {
        return (name != null && !name.isEmpty()) ? name : PlayerConstants.DEFAULT_NAME;
    }

    private static int validatePlayerStat(int stat, int defaultValue) {
        return (stat > 0) ? stat : defaultValue;
    }

}
