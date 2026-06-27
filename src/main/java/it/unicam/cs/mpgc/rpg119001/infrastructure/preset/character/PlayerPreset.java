package it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character;

public record PlayerPreset(
        String id,
        String archetype,
        String displayName,
        int level,
        int experience,
        int experienceCap,
        int currentHealthPoints,
        int healthPoints,
        int attackPoints,
        int attackRange,
        int speed, //ms between moves --> 1000/speed = steps for second, higher is slower
        int attackSpeed,
        String spritePath
) {
}
