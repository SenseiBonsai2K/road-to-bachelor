package it.unicam.cs.mpgc.rpg119001.domain.entity.effect;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;

/**
 * Represents an effect that increases the player's movement speed.
 *
 * <p>The speed bonus is defined at construction time and applied when
 * the effect is executed.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Increase the player's movement speed.</li>
 * </ul>
 *
 * <h2>Design Notes</h2>
 * <p>This implementation can be reused by consumable items, temporary
 * buffs or future gameplay mechanics that modify movement speed.</p>
 */
public class SpeedEffect implements Effect {

    private final int amount;

    public SpeedEffect(int amount) {
        this.amount = amount;
    }

    @Override
    public void apply(Player player) {
        player.setSpeed(
                player.getSpeed() + amount
        );
    }
}
