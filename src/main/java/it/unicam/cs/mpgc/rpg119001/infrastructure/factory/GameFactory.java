package it.unicam.cs.mpgc.rpg119001.infrastructure.factory;

import it.unicam.cs.mpgc.rpg119001.config.Constants.GameConstants;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.PlayerPreset;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.infrastructure.room.RoomTemplateDTO;
import it.unicam.cs.mpgc.rpg119001.infrastructure.room.RoomTemplateRepository;

public class GameFactory {
    public Game createNewGame(PlayerPreset preset) {
        RoomTemplateDTO dto = new RoomTemplateRepository().randomTemplate();
        Room startRoom = new RoomFactory().createRoom(GameConstants.START_GAME_LEVEL, dto);
        Player player = new Player(preset, startRoom.getPlayerSpawnPosition());
        Game game = new Game(player, startRoom, GameConstants.START_GAME_LEVEL);
        game.setPlayer(player);
        return game;
    }
}
