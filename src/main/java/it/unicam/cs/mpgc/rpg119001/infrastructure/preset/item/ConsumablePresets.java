package it.unicam.cs.mpgc.rpg119001.infrastructure.preset.item;

import it.unicam.cs.mpgc.rpg119001.config.Constants.ConsumableSpritePathConstants;
import it.unicam.cs.mpgc.rpg119001.domain.entity.effect.HealEffect;
import it.unicam.cs.mpgc.rpg119001.domain.entity.effect.SpeedEffect;

import java.util.List;
import java.util.Map;

public final class ConsumablePresets {
    public static final ConsumablePreset HEAL_POTION =
            new ConsumablePreset(
                    "heal-potion",
                    "Heal Potion",
                    ConsumableSpritePathConstants.HEAL_POTION,
                    List.of(
                            new HealEffect(20)
                    )
            );

    public static final ConsumablePreset SPEED_POTION =
            new ConsumablePreset(
                    "speed-potion",
                    "Speed Potion",
                    ConsumableSpritePathConstants.SPEED_POTION,
                    List.of(
                            new SpeedEffect(200)
                    )
            );

    public static final List<ConsumablePreset> ALL = List.of(
            HEAL_POTION,
            SPEED_POTION
    );

    private static final Map<String, ConsumablePreset> BY_ID = Map.of(
            HEAL_POTION.id(), HEAL_POTION,
            SPEED_POTION.id(), SPEED_POTION
    );

    public static List<ConsumablePreset> getAll() {
        return ALL;
    }

    public static ConsumablePreset getById(String id) {
        return BY_ID.get(id);
    }
}
