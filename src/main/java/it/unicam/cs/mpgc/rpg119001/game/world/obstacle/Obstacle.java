package it.unicam.cs.mpgc.rpg119001.game.world.obstacle;

import it.unicam.cs.mpgc.rpg119001.game.entity.Player;
import it.unicam.cs.mpgc.rpg119001.game.world.Position;

public interface Obstacle {

    Position getPosition();

    void interact(Player player);

    boolean isPassable();
}
