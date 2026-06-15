package it.unicam.cs.mpgc.rpg119001.infrastructure.preset.item;

import it.unicam.cs.mpgc.rpg119001.config.Constants.WeaponSpritePathConstants;

import java.util.List;
import java.util.Map;

public final class WeaponPresets {
    public static final WeaponPreset LONG_SWORD =
            new WeaponPreset(
                    "long-sword",
                    "Long Sword",
                    WeaponSpritePathConstants.LONG_SWORD,
                    20,
                    100,
                    1
            );

    public static final WeaponPreset WOOD_STAFF =
            new WeaponPreset(
                    "heavy-armor",
                    "Heavy Armor",
                    WeaponSpritePathConstants.WOOD_STAFF,
                    30,
                    60,
                    6
            );

    public static final List<WeaponPreset> ALL = List.of(
            LONG_SWORD,
            WOOD_STAFF
    );

    private static final Map<String, WeaponPreset> BY_ID = Map.of(
            LONG_SWORD.id(), LONG_SWORD,
            WOOD_STAFF.id(), WOOD_STAFF
    );

    public static List<WeaponPreset> getAll() {
        return ALL;
    }

    public static WeaponPreset getById(String id) {
        return BY_ID.get(id);
    }
}
