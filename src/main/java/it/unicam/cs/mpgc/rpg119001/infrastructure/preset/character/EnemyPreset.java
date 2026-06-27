package it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character;

public record EnemyPreset(
        String id,
        String archetype,
        String displayName,
        int currentHealthPoints,
        int healthPoints,
        int attackPoints,
        int attackRange, //tiles
        int speed, //ms between moves --> 1000/speed = steps for second, higher is slower
        int attackSpeed, //ms between attacks
        int experiencePointsReward,
        String spritePath
) {
}
