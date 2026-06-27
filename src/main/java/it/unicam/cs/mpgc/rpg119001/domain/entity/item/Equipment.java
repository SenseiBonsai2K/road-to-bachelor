package it.unicam.cs.mpgc.rpg119001.domain.entity.item;

/**
 * Represents the base abstraction for equippable items.
 *
 * <p>Equipment defines the common characteristics shared by all items
 * that can be worn or equipped by the player, such as weapons and armor.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Provide a common abstraction for all equipment items.</li>
 *     <li>Share common item properties inherited from {@link Item}.</li>
 * </ul>
 *
 * <h2>Design Notes</h2>
 * <p>This abstract class allows different equipment categories to share
 * common behavior while defining their own gameplay attributes.</p>
 */
public abstract class Equipment extends Item {

    protected Equipment(String id, String name, String spritePath) {
        super(id, name, spritePath);
    }
}
