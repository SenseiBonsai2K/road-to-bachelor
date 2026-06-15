package it.unicam.cs.mpgc.rpg119001.infrastructure.preset.item;

import it.unicam.cs.mpgc.rpg119001.config.Constants.ArmorSpritePathConstants;

import java.util.List;
import java.util.Map;

public final class ArmorPresets {
    public static final ArmorPreset LEATHER_ARMOR =
                new ArmorPreset(
                    "leather-armor",
                    "Leather Armor",
                    ArmorSpritePathConstants.LEATHER_ARMOR,
                    20,
                    60,
                    0
            );

    public static final ArmorPreset HEAVY_ARMOR =
            new ArmorPreset(
                    "heavy-armor",
                    "Heavy Armor",
                    ArmorSpritePathConstants.HEAVY_ARMOR,
                    40,
                    100,
                    0
            );

    public static final List<ArmorPreset> ALL = List.of(
            LEATHER_ARMOR,
            HEAVY_ARMOR
    );

    private static final Map<String, ArmorPreset> BY_ID = Map.of(
            LEATHER_ARMOR.id(), LEATHER_ARMOR,
            HEAVY_ARMOR.id(), HEAVY_ARMOR
    );

    public static List<ArmorPreset> getAll() {
        return ALL;
    }

    public static ArmorPreset getById(String id) {
        return BY_ID.get(id);
    }
}
