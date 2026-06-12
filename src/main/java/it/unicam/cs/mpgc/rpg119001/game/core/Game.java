package it.unicam.cs.mpgc.rpg119001.game.core;

import it.unicam.cs.mpgc.rpg119001.game.entity.Player;
import it.unicam.cs.mpgc.rpg119001.game.world.Room;

public class Game {
    private Player player;
    private Room currentRoom;
    private int currentlevel;

    public Game(Player player, Room currentRoom, int level) {
        this.player = player;
        this.currentRoom = currentRoom;
        this.currentlevel = level;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public int getLevel() {
        return currentlevel;
    }
}
