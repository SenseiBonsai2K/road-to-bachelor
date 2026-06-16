package it.unicam.cs.mpgc.rpg119001.domain.world;

import it.unicam.cs.mpgc.rpg119001.config.Constants.TileSpritePathConstants;

public enum TileType {

    FLOOR(true, TileSpritePathConstants.FLOOR),
    WALL(false, TileSpritePathConstants.WALL),
    DOOR(false, TileSpritePathConstants.DOOR);

    private final boolean walkable;
    private final String spritePath;

    TileType(boolean walkable, String spritePath) {
        this.walkable = walkable;
        this.spritePath = spritePath;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public String getSpritePath() {
        return this.spritePath;
    }
}
