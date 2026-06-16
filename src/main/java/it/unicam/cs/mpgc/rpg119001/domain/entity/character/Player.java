package it.unicam.cs.mpgc.rpg119001.domain.entity.character;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.PlayerPreset;

import java.util.List;

import static it.unicam.cs.mpgc.rpg119001.config.Constants.GameConstants.MS_PER_SECOND;

public class Player extends Character {

    private final PlayerPreset preset;

    public Player(PlayerPreset preset, GridPosition gridPosition) {
        super(
                preset.id(),
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

    @Override
    public boolean blocksMovement(){
        return true;
    }

    public List<String> getStats() {
        return List.of(
                "Health: "+preset.healthPoints(),
                "Attack: "+preset.attackPoints(),
                "Range: "+preset.attackRange(),
                "Speed: " + String.format("%.2f", MS_PER_SECOND / preset.speed()) + " tiles/s",
                "Atk Speed: " + String.format("%.2f", MS_PER_SECOND / preset.attackSpeed()) + " hit/s"
        );
    }
}
