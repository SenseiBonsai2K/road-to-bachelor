package it.unicam.cs.mpgc.rpg119001.domain.entity.character;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.EnemyPreset;

import java.util.List;

import static it.unicam.cs.mpgc.rpg119001.config.Constants.GameConstants.MS_PER_SECOND;

/**
 * Represents an enemy controlled by the game.
 *
 * <p>An enemy is a combat-capable character created from an
 * {@link EnemyPreset}. Besides common combat behavior inherited from
 * {@link Character}, it exposes metadata such as archetype, display name
 * and experience reward.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Represent hostile characters within the game world.</li>
 *     <li>Expose enemy-specific metadata.</li>
 *     <li>Block movement and line of sight.</li>
 *     <li>Provide formatted statistics for the user interface.</li>
 * </ul>
 *
 * <h2>Design Notes</h2>
 * <p>Enemy statistics are initialized from immutable presets, while
 * runtime values such as health are managed by the inherited
 * {@link Character} implementation.</p>
 */
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
                "Health: "+this.getCurrentHealthPoints()+" / "+this.getHealthPoints(),
                "Attack: "+this.getAttackPoints(),
                "Range: "+this.getAttackRange(),
                "Speed: " + String.format("%.2f", MS_PER_SECOND / this.getSpeed()) + " tiles/s",
                "Atk Speed: " + String.format("%.2f", MS_PER_SECOND / this.getAttackSpeed()) + " hit/s"
        );
    }
}