package it.unicam.cs.mpgc.rpg119001.domain.entity.item;

import it.unicam.cs.mpgc.rpg119001.domain.entity.effect.Effect;

import java.util.List;

/**
 * Represents a consumable item that applies one or more gameplay effects.
 *
 * <p>A consumable encapsulates a collection of {@link Effect} instances
 * that are executed when the item is used by the player.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Store the effects associated with the item.</li>
 *     <li>Provide access to the configured effects.</li>
 * </ul>
 *
 * <h2>Design Notes</h2>
 * <p>This class follows a composition-based approach by delegating its
 * behavior to reusable {@link Effect} implementations.</p>
 */
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
