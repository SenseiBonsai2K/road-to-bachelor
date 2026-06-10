package it.unicam.cs.mpgc.rpg119001.game.factory;

import it.unicam.cs.mpgc.rpg119001.game.config.Constants.GameConstants;
import it.unicam.cs.mpgc.rpg119001.game.entity.Player;
import it.unicam.cs.mpgc.rpg119001.game.core.Game;
import it.unicam.cs.mpgc.rpg119001.game.preset.PlayerPreset;
import it.unicam.cs.mpgc.rpg119001.game.world.Room;

public class GameFactory {
    public static Game createNewGame(PlayerPreset preset) {
        Room startRoom = RoomFactory.createRoom(GameConstants.START_GAME_LEVEL);
        Player player = new Player(preset, startRoom.getSpawnPosition());
        Game game = new Game(player, startRoom, GameConstants.START_GAME_LEVEL);
        game.setPlayer(player);
        return game;
    }
}
