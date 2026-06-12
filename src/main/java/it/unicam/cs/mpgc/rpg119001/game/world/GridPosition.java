package it.unicam.cs.mpgc.rpg119001.game.world;

import it.unicam.cs.mpgc.rpg119001.game.config.Constants.GridConstants;

public class GridPosition {
    private final int tileX;
    private final int tileY;

    public GridPosition(int tileX, int tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
    }

    public GridPosition translate(int dx, int dy) {
        return new GridPosition(
                tileX + dx,
                tileY + dy
        );
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public double toPixelX() {
        return tileX * GridConstants.TILE_SIZE;
    }

    public double toPixelY() {
        return tileY * GridConstants.TILE_SIZE;
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
