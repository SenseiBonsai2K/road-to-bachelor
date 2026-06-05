package it.unicam.cs.mpgc.rpg119001.model;

/**
 * The Position class represents the coordinates of a character or an object in the RPG game.
 * It contains x and y coordinates and provides methods to get and set these values.
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
