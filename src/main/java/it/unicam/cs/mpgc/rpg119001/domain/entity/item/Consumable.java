package it.unicam.cs.mpgc.rpg119001.domain.entity.item;

import it.unicam.cs.mpgc.rpg119001.domain.entity.effect.Effect;

import java.util.List;

public class Consumable extends Item {

    private final List<Effect> effects;

    public Consumable(String id, String name, String spritePath, List<Effect> effects) {
        super(id, name, spritePath);
        this.effects = effects;
    }

    public List<Effect> getEffects() {
        return effects;
    }
}
