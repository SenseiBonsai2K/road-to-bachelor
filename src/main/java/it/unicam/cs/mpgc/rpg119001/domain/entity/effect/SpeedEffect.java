package it.unicam.cs.mpgc.rpg119001.domain.entity.effect;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;

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
