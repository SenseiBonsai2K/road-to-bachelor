package it.unicam.cs.mpgc.rpg119001.infrastructure.preset;

public record EnemyPreset(
        String id,
        String archetype,
        String displayName,
        int baseHealthPoints,
        int baseAttackPoints,
        int baseSpeed, //ms between moves --> 1000/speed = steps for second, higher is slower
        int experiencePointsReward,
        String imagePath
) {
}
