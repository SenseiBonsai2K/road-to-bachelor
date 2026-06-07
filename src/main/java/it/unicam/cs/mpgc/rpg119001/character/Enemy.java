package it.unicam.cs.mpgc.rpg119001.character;

import it.unicam.cs.mpgc.rpg119001.model.Position;
import it.unicam.cs.mpgc.rpg119001.preset.EnemyPreset;
import it.unicam.cs.mpgc.rpg119001.preset.EnemyPresets;

public class Enemy extends Character {

    public final EnemyPreset enemyPreset;

    public Enemy(EnemyPreset enemyPreset, Position position) {
        super(
            enemyPreset.baseHealthPoints(),
            enemyPreset.baseAttackPoints(),
            position,
            enemyPreset.baseSpeed()
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