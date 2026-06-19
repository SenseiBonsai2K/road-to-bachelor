package it.unicam.cs.mpgc.rpg119001.domain.game;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

public class Game {
    private Player player;
    private Room currentRoom;
    private int currentLevel;

    public Game(Player player, Room currentRoom, int level) {
        this.player = player;
        this.currentRoom = currentRoom;
        this.currentLevel = level;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Room getCurrentRoom() { return currentRoom; }

    public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom; }

    public int getLevel() {
        return currentLevel;
    }

    public void nextLevel() {
        ++this.currentLevel;
    }
}
