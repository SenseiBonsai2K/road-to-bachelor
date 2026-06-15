package it.unicam.cs.mpgc.rpg119001.domain.world;

public class Tile {
    private final TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }

    public boolean isWalkable() {
        return type.isWalkable();
    }

    public String getSpritePath() {
        return type.getSpritePath();
    }
}
