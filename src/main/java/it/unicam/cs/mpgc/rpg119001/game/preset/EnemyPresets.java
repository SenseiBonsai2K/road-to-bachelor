package it.unicam.cs.mpgc.rpg119001.game.preset;

import it.unicam.cs.mpgc.rpg119001.game.config.Constants.EnemyImagePathConstants;

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
                2,
                10,
                EnemyImagePathConstants.GOBLIN
            );

    public static final EnemyPreset ORC =
            new EnemyPreset(
                "orc",
                "Orc",
                "Orc",
                50,
                10,
                3,
                20,
                EnemyImagePathConstants.ORC
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
