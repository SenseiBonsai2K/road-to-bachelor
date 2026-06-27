package it.unicam.cs.mpgc.rpg119001.domain.entity.effect;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;

/**
 * Represents an effect that increases the player's maximum health.
 *
 * <p>The amount of health granted is defined at construction time and
 * applied when the effect is executed.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Increase the player's health points.</li>
 * </ul>
 *
 * <h2>Design Notes</h2>
 * <p>This implementation is intentionally simple and serves as a reusable
 * gameplay effect for healing items or future abilities.</p>
 */
public class HealEffect implements Effect {

    private final int amount;

    public HealEffect(int amount) {
        this.amount = amount;
    }

    @Override
    public void apply(Player player) {
        player.setHealthPoints(
                player.getHealthPoints() + amount
        );
    }
}
