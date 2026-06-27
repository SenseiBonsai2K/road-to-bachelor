package it.unicam.cs.mpgc.rpg119001.infrastructure.preset.item;

import it.unicam.cs.mpgc.rpg119001.domain.entity.effect.Effect;

import java.util.List;

public record ConsumablePreset(
    String id,
    String displayName,
    String spritePath,
    List<Effect> effects
) {
}
