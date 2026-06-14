package it.unicam.cs.mpgc.rpg119001.domain.entity;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.PlayerPreset;

public class Player extends Character {

    public final PlayerPreset playerPreset;

    public Player(PlayerPreset playerPreset, GridPosition gridPosition) {
        super(
            playerPreset.baseHealthPoints(),
            playerPreset.baseAttackPoints(),
            gridPosition,
            playerPreset.baseSpeed(),
            playerPreset.imagePath()
        );
        this.playerPreset = playerPreset;
    }

    public String getName() {
        return this.playerPreset.displayName();
    }

    public String getArchetype() {
        return this.playerPreset.archetype();
    }
}
