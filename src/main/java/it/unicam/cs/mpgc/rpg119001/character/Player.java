package it.unicam.cs.mpgc.rpg119001.character;

import it.unicam.cs.mpgc.rpg119001.model.Position;
import it.unicam.cs.mpgc.rpg119001.preset.PlayerPreset;
import it.unicam.cs.mpgc.rpg119001.util.Constants.GameConstants;

public class Player extends Character {

    public final PlayerPreset playerPreset;

    public Player(PlayerPreset playerPreset, Position position) {
        super(
                playerPreset.baseHealthPoints(),
                playerPreset.baseAttackPoints(),
                GameConstants.PLAYER_START_POSITION,
                playerPreset.baseSpeed()
        );
        this.playerPreset = playerPreset;
    }

    public String getName() {
        return this.playerPreset.displayName();
    }

    public String getArchetype() {
        return this.playerPreset.archetype();
    }
}
