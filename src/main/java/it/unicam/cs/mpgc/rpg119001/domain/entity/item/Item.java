package it.unicam.cs.mpgc.rpg119001.domain.entity.item;

public abstract class Item {

    private final String id;
    private final String name;
    private final String spritePath;

    protected Item(String id, String name, String spritePath) {
        this.id = id;
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
