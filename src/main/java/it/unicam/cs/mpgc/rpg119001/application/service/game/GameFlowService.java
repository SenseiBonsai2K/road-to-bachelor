package it.unicam.cs.mpgc.rpg119001.application.service.game;

import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.infrastructure.factory.RoomFactory;
import it.unicam.cs.mpgc.rpg119001.infrastructure.room.RoomTemplateDTO;
import it.unicam.cs.mpgc.rpg119001.infrastructure.room.RoomTemplateRepository;

public class GameFlowService {

    private final RoomFactory roomFactory;
    private final RoomTemplateRepository repository;

    public GameFlowService(RoomFactory roomFactory,
                           RoomTemplateRepository repository) {
        this.roomFactory = roomFactory;
        this.repository = repository;
    }

    public Room nextRoom(int level) {

        RoomTemplateDTO dto = selectTemplate(level);

        return roomFactory.createNewRoom(level, dto);
    }

    private RoomTemplateDTO selectTemplate(int level) {
        if (level % 5 == 0) {
            return repository.randomBossTemplate();
        }
        return repository.randomTemplate();
    }

    public void advanceToNextRoom(Game game) {

        game.nextLevel();

        Room newRoom = nextRoom(game.getLevel());

        game.setCurrentRoom(newRoom);

        game.getPlayer().setGridPosition(newRoom.getPlayerSpawnPosition());
    }
}
