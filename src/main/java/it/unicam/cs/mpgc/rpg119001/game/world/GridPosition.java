package it.unicam.cs.mpgc.rpg119001.game.world;

/**
 * Represents a position on the tile grid.
 * Each tile is 16x16 pixels.
 * Coordinates are in tile units, not pixels.
 */
public class GridPosition {
    private int tileX;
    private int tileY;

    public GridPosition(int tileX, int tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public double toPixelX() {
        return tileX * 16;
    }

    public double toPixelY() {
        return tileY * 16;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GridPosition gridPosition)) return false;
        return tileX == gridPosition.tileX && tileY == gridPosition.tileY;
    }

    @Override
    public int hashCode() {
        return 31 * tileX + tileY;
    }

    @Override
    public String toString() {
        return String.format("GridPosition(%d, %d)", tileX, tileY);
    }
}
