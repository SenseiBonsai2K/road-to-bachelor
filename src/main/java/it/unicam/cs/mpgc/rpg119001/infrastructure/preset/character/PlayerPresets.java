package it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character;

import it.unicam.cs.mpgc.rpg119001.config.Constants.PlayerSpritePathConstants;

import java.util.List;
import java.util.Map;

public final class PlayerPresets {

    public static final PlayerPreset WARRIOR =
            new PlayerPreset(
                    "warrior",
                    "Warrior",
                    "Cristian",
                    1,
                    0,
                    100,
                    100,
                    100,
                    20,
                    1,
                    140,
                    500,
                    PlayerSpritePathConstants.WARRIOR
            );

    public static final PlayerPreset MAGE =
            new PlayerPreset(
                    "mage",
                    "Mage",
                    "Gandalf",
                    1,
                    0,
                    80,
                    80,
                    80,
                    30,
                    6,
                    180,
                    800,
                    PlayerSpritePathConstants.MAGE
            );

    public static final List<PlayerPreset> ALL = List.of(
            WARRIOR,
            MAGE
    );

    private static final Map<String, PlayerPreset> BY_ID = Map.of(
            WARRIOR.id(), WARRIOR,
            MAGE.id(), MAGE
    );

    private static final Map<String, PlayerPreset> BY_ARCHETYPE = Map.of(
            WARRIOR.archetype(), WARRIOR,
            MAGE.archetype(), MAGE
    );

    public static List<PlayerPreset> getAll() {
        return ALL;
    }

    public static PlayerPreset getById(String id) {
        return BY_ID.get(id);
    }

    public static PlayerPreset getByArchetype(String archetype) { return BY_ARCHETYPE.get(archetype); }
}
