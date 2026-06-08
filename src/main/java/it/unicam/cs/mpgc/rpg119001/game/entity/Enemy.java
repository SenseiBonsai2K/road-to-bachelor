package it.unicam.cs.mpgc.rpg119001.game.entity;

import it.unicam.cs.mpgc.rpg119001.game.world.Position;
import it.unicam.cs.mpgc.rpg119001.game.preset.EnemyPreset;

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