package it.unicam.cs.mpgc.rpg119001.domain.world;

public class Tile {
    private TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public boolean isWalkable() {return type.isWalkable();}

    public boolean blocksLineOfSight() { return type.blocksLineOfSight();}

    public String getSpritePath() {return type.getSpritePath();}
}
