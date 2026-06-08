package it.unicam.cs.mpgc.rpg119001.game.preset;

public record PlayerPreset(
        String id,
        String archetype,
        String displayName,
        int baseHealthPoints,
        int baseAttackPoints,
        double baseSpeed
) {
}
