package it.unicam.cs.mpgc.rpg119001.domain.world;

import it.unicam.cs.mpgc.rpg119001.config.Constants.TileSpritePathConstants;

public enum TileType {

    FLOOR(true, TileSpritePathConstants.FLOOR, false),
    WALL(false, TileSpritePathConstants.WALL, true),
    DOOR(false, TileSpritePathConstants.DOOR, true);

    private final boolean walkable;
    private final String spritePath;
    private final boolean blocksLineOfSight;

    TileType(boolean walkable, String spritePath, boolean blocksLineOfSight) {
        this.walkable = walkable;
        this.spritePath = spritePath;
        this.blocksLineOfSight = blocksLineOfSight;
    }

    public boolean isWalkable() {return walkable;}
    public boolean blocksLineOfSight() {return blocksLineOfSight;}
    public String getSpritePath() {return this.spritePath;}
}
