package it.unicam.cs.mpgc.rpg119001.model;

import it.unicam.cs.mpgc.rpg119001.util.CharacterConstants.PlayerConstants;

public class Player extends Character {

    public Player(String name, int healthPoints, int attackPoints) {
        super(
                validatePlayerName(name),
                validatePlayerStat(healthPoints, PlayerConstants.HEALTH_POINTS),
                validatePlayerStat(attackPoints, PlayerConstants.ATTACK_POINTS)
        );
    }

    private static String validatePlayerName(String name) {
        return (name != null && !name.isEmpty()) ? name : PlayerConstants.DEFAULT_NAME;
    }

    private static int validatePlayerStat(int stat, int defaultValue) {
        return (stat > 0) ? stat : defaultValue;
    }

}
