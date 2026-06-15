package it.unicam.cs.mpgc.rpg119001.domain.entity.item;

public abstract class Equipment extends Item {

    protected Equipment(
            String id,
            String name,
            String spritePath
    ) {
        super(id, name, spritePath);
    }
}
