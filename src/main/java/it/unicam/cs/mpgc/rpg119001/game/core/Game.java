package it.unicam.cs.mpgc.rpg119001.game.core;

import it.unicam.cs.mpgc.rpg119001.game.entity.Enemy;
import it.unicam.cs.mpgc.rpg119001.game.entity.Player;
import it.unicam.cs.mpgc.rpg119001.game.world.Room;

import java.util.List;

public class Game {
    private Player player;
    private Room currenRoom;
    private int currentLevel;

    public Game(Player player, Room currentRoom, int currentLevel) {
        this.player = player;
        this.currenRoom = currentRoom;
        this.currentLevel = currentLevel;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Room getCurrentRoom() {
        return currenRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currenRoom = currentRoom;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
