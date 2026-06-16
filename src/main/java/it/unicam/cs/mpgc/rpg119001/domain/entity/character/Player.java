package it.unicam.cs.mpgc.rpg119001.domain.entity.character;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.PlayerPreset;

public class Player extends Character {

    private final PlayerPreset preset;

    public Player(PlayerPreset preset, GridPosition gridPosition) {
        super(
                preset.id(),
                preset.baseHealthPoints(),
                preset.baseAttackPoints(),
                preset.baseAttackRange(),
                gridPosition,
                preset.baseSpeed(),
                preset.spritePath()
        );
        this.preset = preset;
    }

    public String getArchetype() {return this.preset.archetype();}
    public String getDisplayName() {return this.preset.displayName();}

    @Override
    public boolean blocksMovement(){
        return true;
    }
}
