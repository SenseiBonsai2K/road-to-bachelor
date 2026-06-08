package it.unicam.cs.mpgc.rpg119001.game.world;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {

        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(
                    "Position coordinates must be non-negative."
            );
        }

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
