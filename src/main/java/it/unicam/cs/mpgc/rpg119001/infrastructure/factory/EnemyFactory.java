package it.unicam.cs.mpgc.rpg119001.infrastructure.factory;

import it.unicam.cs.mpgc.rpg119001.config.Constants.EnemyGameConstants;
import it.unicam.cs.mpgc.rpg119001.config.Constants.GameConstants;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.EnemyPreset;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.EnemyPresets;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

/**
 * Factory responsible for creating enemy instances based on predefined
 * archetypes and the current game progression.
 *
 * <p>This factory applies level scaling and difficulty modifiers to enemy
 * statistics while instantiating enemies from reusable presets.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Create random enemies for room generation.</li>
 *     <li>Create enemies from a specific archetype.</li>
 *     <li>Scale enemy attributes according to game level.</li>
 *     <li>Apply the current game difficulty multiplier.</li>
 * </ul>
 */
public class EnemyFactory {
    public Enemy createRandomEnemy(int level) {
        EnemyPreset preset = EnemyPresets.getAll().get((int) (Math.random() * EnemyPresets.getAll().size()));
        return createEnemy(level, preset);
    }

    public Enemy createEnemyByArchetype(int level, String archetype) {
        EnemyPreset preset = EnemyPresets.getByArchetype(archetype);
        return createEnemy(level, preset);
    }

    private Enemy createEnemy(int level, EnemyPreset preset) {
        double multiplier = getDifficultyMultiplier();

        int hp = (int) ((preset.healthPoints() + level * EnemyGameConstants.HEALTH_POINTS_PER_LEVEL) * multiplier);
        int atk = (int) ((preset.attackPoints() + level * EnemyGameConstants.ATTACK_POINTS_PER_LEVEL) * multiplier);

        Enemy enemy = new Enemy(preset, new GridPosition(0, 0));

        enemy.setHealthPoints(hp);
        enemy.setAttackPoints(atk);
        enemy.setCurrentHealthPoints(hp);

        return enemy;
    }

    //Actually the difficulty is always the same
    //TODO implementing in settings at main menu difficulty selection
    private double getDifficultyMultiplier() {
        return switch (GameConstants.DIFFICULTY) {
            case 1 -> 1.0;  // Easy
            case 2 -> 1.4; // Normal
            case 3 -> 1.8;  // Hard
            default -> 1.0;
        };
    }
}
