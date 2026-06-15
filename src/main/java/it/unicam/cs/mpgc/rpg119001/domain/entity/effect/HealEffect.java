package it.unicam.cs.mpgc.rpg119001.domain.entity.effect;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;

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
