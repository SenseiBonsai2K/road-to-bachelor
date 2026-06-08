package it.unicam.cs.mpgc.rpg119001.game.preset;

public record EnemyPreset(
        String id,
        String archetype,
        String displayName,
        int baseHealthPoints,
        int baseAttackPoints,
        double baseSpeed,
        int experiencePointsReward
) {
}
