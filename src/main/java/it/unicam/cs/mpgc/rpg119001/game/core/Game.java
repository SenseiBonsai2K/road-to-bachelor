package it.unicam.cs.mpgc.rpg119001.game.core;

import it.unicam.cs.mpgc.rpg119001.game.entity.Enemy;
import it.unicam.cs.mpgc.rpg119001.game.entity.Player;
import it.unicam.cs.mpgc.rpg119001.game.world.Room;

import java.util.List;

public class Game {
    private Player player;
    private List<Enemy> enemies;
    private Room room;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
