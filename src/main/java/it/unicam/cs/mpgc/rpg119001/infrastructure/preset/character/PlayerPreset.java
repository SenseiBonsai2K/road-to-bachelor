package it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character;

public record PlayerPreset(
        String id,
        String archetype,
        String displayName,
        int baseHealthPoints,
        int baseAttackPoints,
        int baseSpeed, //ms between moves --> 1000/speed = steps for second, higher is slower
        String spritePath
) {
}
