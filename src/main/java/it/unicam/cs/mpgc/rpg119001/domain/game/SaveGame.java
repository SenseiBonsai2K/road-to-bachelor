package it.unicam.cs.mpgc.rpg119001.domain.game;

import java.util.ArrayList;
import java.util.List;

public class SaveGame {

    private int level;

    private String roomTemplate;

    private PlayerState playerState;

    private List<EnemyState> enemyStates = new ArrayList<>();

    public static class PlayerState {
        public String archetype;
        public int currentHp;
        public int exp;
        public int level;
        public int x;
        public int y;
    }

    public static class EnemyState {
        public String archetype;
        public int currentHp;
        public int x;
        public int y;
    }

    public SaveGame() {}

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