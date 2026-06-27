package it.unicam.cs.mpgc.rpg119001.domain.entity.effect;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;

/**
 * Defines the contract for gameplay effects that can be applied to the player.
 *
 * <p>Effects encapsulate modifications to player attributes, allowing
 * consumables and future gameplay mechanics to reuse a common abstraction.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Apply a gameplay effect to a player.</li>
 * </ul>
 *
 * <h2>Design Notes</h2>
 * <p>This interface follows the Strategy pattern, allowing new effects
 * to be introduced without modifying existing gameplay systems.</p>
 */
public interface Effect {
    void apply(Player player);
}
