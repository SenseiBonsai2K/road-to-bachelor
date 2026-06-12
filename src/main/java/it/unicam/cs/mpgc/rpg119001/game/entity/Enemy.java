package it.unicam.cs.mpgc.rpg119001.game.entity;

import it.unicam.cs.mpgc.rpg119001.game.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.game.preset.EnemyPreset;

public class Enemy extends Character {

    public final EnemyPreset enemyPreset;

    public Enemy(EnemyPreset enemyPreset, GridPosition gridPosition) {
        super(
            enemyPreset.baseHealthPoints(),
            enemyPreset.baseAttackPoints(),
            gridPosition,
            enemyPreset.baseSpeed(),
            enemyPreset.imagePath()
         );
        this.enemyPreset = enemyPreset;
    }

    public String getName() {
        return this.enemyPreset.displayName();
    }

    public String getArchetype() {
        return this.enemyPreset.archetype();
    }
}