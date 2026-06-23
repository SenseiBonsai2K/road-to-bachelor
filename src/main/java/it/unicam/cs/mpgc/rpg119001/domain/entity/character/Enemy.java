package it.unicam.cs.mpgc.rpg119001.domain.entity.character;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.EnemyPreset;

import java.util.List;

import static it.unicam.cs.mpgc.rpg119001.config.Constants.GameConstants.MS_PER_SECOND;

public class Enemy extends Character {

    private final EnemyPreset preset;

    public Enemy(EnemyPreset preset, GridPosition gridPosition) {
        super(
                preset.id(),
                preset.currentHealthPoints(),
                preset.healthPoints(),
                preset.attackPoints(),
                preset.attackRange(),
                gridPosition,
                preset.speed(),
                preset.attackSpeed(),
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
    @Override
    public boolean blocksLineOfSight(){return true;}

    public List<String> getStats() {
        return List.of(
                "Health: "+this.getHealthPoints(),
                "Attack: "+this.getAttackPoints(),
                "Range: "+this.getAttackRange(),
                "Speed: " + String.format("%.2f", MS_PER_SECOND / this.getSpeed()) + " tiles/s",
                "Atk Speed: " + String.format("%.2f", MS_PER_SECOND / this.getAttackSpeed()) + " hit/s"
        );
    }
}