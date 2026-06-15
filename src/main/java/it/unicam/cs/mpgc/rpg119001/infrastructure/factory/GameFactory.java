package it.unicam.cs.mpgc.rpg119001.infrastructure.factory;

import it.unicam.cs.mpgc.rpg119001.config.Constants.GameConstants;
import it.unicam.cs.mpgc.rpg119001.domain.entity.Player;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.PlayerPreset;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

public class GameFactory {
    public Game createNewGame(PlayerPreset preset) {
        Room startRoom = new RoomFactory(new EnemyFactory()).createRoom(GameConstants.START_GAME_LEVEL);
        Player player = new Player(preset, startRoom.getPlayerSpawnPosition());
        Game game = new Game(player, startRoom, GameConstants.START_GAME_LEVEL);
        game.setPlayer(player);
        return game;
    }
}
