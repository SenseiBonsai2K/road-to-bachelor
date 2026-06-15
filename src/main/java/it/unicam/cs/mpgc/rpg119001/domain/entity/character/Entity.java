package it.unicam.cs.mpgc.rpg119001.domain.entity.character;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

public abstract class Entity {
    private final String id;
    private GridPosition position;
    private final String spritePath;

    public Entity(String id, GridPosition position, String spritePath) {
        this.id = id;
        this.position = position;
        this.spritePath = spritePath;
    }

    public String getId() {
        return id;
    }

    public GridPosition getGridPosition() {
        return position;
    }

    public void setGridPosition(GridPosition position) {
        this.position = position;
    }

    public String getSpritePath() {
        return this.spritePath;
    }
}
