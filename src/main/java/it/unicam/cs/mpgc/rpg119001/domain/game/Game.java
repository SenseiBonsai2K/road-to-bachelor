package it.unicam.cs.mpgc.rpg119001.domain.game;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

/**
 * Represents the complete runtime state of an active game session.
 *
 * <p>A game aggregates the player, the current room and the current
 * progression level, serving as the root object of the domain model
 * during gameplay.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Maintain the current player instance.</li>
 *     <li>Maintain the currently loaded room.</li>
 *     <li>Track game progression.</li>
 * </ul>
 */
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
