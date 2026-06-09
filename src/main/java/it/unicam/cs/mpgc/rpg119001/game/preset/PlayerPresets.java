package it.unicam.cs.mpgc.rpg119001.game.preset;

import it.unicam.cs.mpgc.rpg119001.game.config.Constants.PlayerImagePathConstants;

import java.util.List;
import java.util.Map;

public final class PlayerPresets {

    public static final PlayerPreset WARRIOR =
            new PlayerPreset(
                    "warrior",
                    "Warrior",
                    "Cristian",
                    100,
                    20,
                    1.0,
                    PlayerImagePathConstants.WARRIOR
            );

    public static final PlayerPreset MAGE =
            new PlayerPreset(
                    "mage",
                    "Mage",
                    "Gandalf",
                    80,
                    30,
                    1.2,
                    PlayerImagePathConstants.MAGE
            );

    public static final List<PlayerPreset> ALL = List.of(
            WARRIOR,
            MAGE
    );

    private static final Map<String, PlayerPreset> BY_ID = Map.of(
            WARRIOR.id(), WARRIOR,
            MAGE.id(), MAGE
    );

    public static List<PlayerPreset> getAll() {
        return ALL;
    }

    public static PlayerPreset getById(String id) {
        return BY_ID.get(id);
    }
}
