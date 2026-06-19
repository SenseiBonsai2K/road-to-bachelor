package it.unicam.cs.mpgc.rpg119001.domain.entity.item;

import it.unicam.cs.mpgc.rpg119001.domain.entity.ItemIdGenerator;

public abstract class Item {

    private final String id;
    private final String name;
    private final String spritePath;

    protected Item(String id, String name, String spritePath) {
        this.id = ItemIdGenerator.next(id);
        this.name = name;
        this.spritePath = spritePath;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpritePath() {
        return spritePath;
    }
}
