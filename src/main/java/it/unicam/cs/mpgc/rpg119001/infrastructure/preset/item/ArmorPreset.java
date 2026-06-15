package it.unicam.cs.mpgc.rpg119001.infrastructure.preset.item;

public record ArmorPreset(
    String id,
    String displayName,
    String spritePath,
    int defenseBonus,
    int healthBonus,
    int speedMalus
) {
}
