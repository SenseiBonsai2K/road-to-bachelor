package it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character;

import it.unicam.cs.mpgc.rpg119001.config.Constants.EnemySpritePathConstants;

import java.util.List;
import java.util.Map;

public final class EnemyPresets {

    public static final EnemyPreset GOBLIN =
            new EnemyPreset(
                "goblin",
                "Goblin",
                "Goblin",
                30,
                5,
                300,
                10,
                EnemySpritePathConstants.GOBLIN
            );

    public static final EnemyPreset ORC =
            new EnemyPreset(
                "orc",
                "Orc",
                "Orc",
                50,
                10,
                500,
                20,
                EnemySpritePathConstants.ORC
            );

    public static final List<EnemyPreset> ALL = List.of(
            GOBLIN,
            ORC
    );

    private static final Map<String, EnemyPreset> BY_ID = Map.of(
            GOBLIN.id(), GOBLIN,
            ORC.id(), ORC
    );

    public static List<EnemyPreset> getAll() {
        return ALL;
    }

    public static EnemyPreset getById(String id) {
        return BY_ID.get(id);
    }
}
