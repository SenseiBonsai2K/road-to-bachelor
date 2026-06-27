package it.unicam.cs.mpgc.rpg119001.domain.entity.item;

import it.unicam.cs.mpgc.rpg119001.application.service.game.ItemIdGenerator;

/**
 * Represents the base abstraction for every item in the game.
 *
 * <p>An item has a unique identifier, a display name and an associated
 * sprite used for rendering. Concrete subclasses define the gameplay
 * behavior of specific item categories.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Provide a unique identifier.</li>
 *     <li>Store the item name.</li>
 *     <li>Store the associated sprite resource.</li>
 * </ul>
 *
 * <h2>Design Notes</h2>
 * <p>This class acts as the root of the item hierarchy and contains only
 * data shared by every item type.</p>
 */
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
