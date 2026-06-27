package it.unicam.cs.mpgc.rpg119001.domain.game;

import java.util.List;

/**
 * Represents a serialized snapshot of the game state used for saving and loading.
 *
 * <p>This class is a Data Transfer Object (DTO) that contains all the
 * information required to reconstruct a previously saved game session,
 * including player state, enemy states and progression data.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Store the current game level.</li>
 *     <li>Store the identifier of the current room template.</li>
 *     <li>Store the full player state required for reconstruction.</li>
 *     <li>Store the state of all enemies in the current room.</li>
 * </ul>
 */
public class SaveGame {

    private final int level;
    private final String roomTemplate;
    private final PlayerState playerState;

    private final List<EnemyState> enemyStates;

    /**
     * Represents the serialized state of the player inside a saved game.
     *
     * <p>This structure contains all essential attributes required to
     * restore a player instance at the exact point of saving.</p>
     */
    public static class PlayerState {
        public String archetype;
        public int currentHp;
        public int exp;
        public int level;
        public int x;
        public int y;
    }

    /**
     * Represents the serialized state of an enemy inside a saved game.
     *
     * <p>Each instance corresponds to a single enemy in the room at the
     * moment of saving, including position, health and archetype.</p>
     */
    public static class EnemyState {
        public String archetype;
        public int currentHp;
        public int x;
        public int y;
    }

    public SaveGame(int level, String roomTemplate,
                    PlayerState playerState,
                    List<EnemyState> enemies) {
        this.level = level;
        this.roomTemplate = roomTemplate;
        this.playerState = playerState;
        this.enemyStates = enemies;
    }

    public int getLevel() {
        return level;
    }

    public String getRoomTemplate() {
        return roomTemplate;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public List<EnemyState> getEnemyStates() {
        return enemyStates;
    }
}