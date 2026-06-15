package it.unicam.cs.mpgc.rpg119001.domain.entity.character;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.EnemyPreset;

public class Enemy extends Character {

    private final EnemyPreset preset;

    public Enemy(EnemyPreset preset, GridPosition gridPosition) {
        super(preset.id(),
                preset.baseHealthPoints(),
                preset.baseAttackPoints(),
                gridPosition,
                preset.baseSpeed(),
                preset.spritePath()
        );
        this.preset = preset;
    }

    public String getArchetype() {return this.preset.archetype();}
    public String getDisplayName() {return this.preset.displayName();}
    public int getExperiencePointsReward() {return this.preset.experiencePointsReward();}

    @Override
    public boolean blocksMovement(){
        return true;
    }
}