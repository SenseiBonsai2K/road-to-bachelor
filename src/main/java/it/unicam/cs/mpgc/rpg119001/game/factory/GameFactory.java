package it.unicam.cs.mpgc.rpg119001.game.factory;

import it.unicam.cs.mpgc.rpg119001.game.entity.Player;
import it.unicam.cs.mpgc.rpg119001.game.core.Game;
import it.unicam.cs.mpgc.rpg119001.game.preset.PlayerPreset;

public class GameFactory {
    public static Game createNewGame(PlayerPreset preset) {
        Game game = new Game();
        Player player = new Player(preset, null);
        game.setPlayer(player);
        // Initialize enemies and room as needed
        return game;
    }
}
