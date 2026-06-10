package it.unicam.cs.mpgc.rpg119001.game.world.obstacle;

import it.unicam.cs.mpgc.rpg119001.game.entity.Player;
import it.unicam.cs.mpgc.rpg119001.game.world.Position;

public class Wall implements Obstacle {

    private final Position position;
    private final double width;
    private final double height;

    public Wall(Position position, double width, double height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void interact(Player player) {
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
