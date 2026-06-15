package it.unicam.cs.mpgc.rpg119001.infrastructure.preset.item;

public record WeaponPreset(
    String id,
    String displayName,
    String spritePath,
    int attackBonus,
    int attackSpeed,
    int attackRange
) {
}
