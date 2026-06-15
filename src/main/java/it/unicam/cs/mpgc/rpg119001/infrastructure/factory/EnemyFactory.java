package it.unicam.cs.mpgc.rpg119001.infrastructure.factory;

import it.unicam.cs.mpgc.rpg119001.config.Constants.GameConstants;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.EnemyPreset;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.EnemyPresets;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

public class EnemyFactory {
    public Enemy createRandomEnemies(int level) {

        EnemyPreset preset = EnemyPresets.getAll().get((int) (Math.random() * EnemyPresets.getAll().size()));

        int hp = preset.baseHealthPoints() + level * GameConstants.HEALTH_POINTS_PER_LEVEL;
        int atk = preset.baseAttackPoints() + level * GameConstants.ATTACK_POINTS_PER_LEVEL;

        // Default starting position (0,0) - will be set by RoomFactory
        Enemy enemy = new Enemy(preset, new GridPosition(0, 0));

        enemy.setHealthPoints(hp);
        enemy.setAttackPoints(atk);

        return enemy;
    }
}
