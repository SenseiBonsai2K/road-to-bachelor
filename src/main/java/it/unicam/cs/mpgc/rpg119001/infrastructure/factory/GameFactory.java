package it.unicam.cs.mpgc.rpg119001.infrastructure.factory;

import it.unicam.cs.mpgc.rpg119001.config.Constants.GameConstants;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.game.SaveGame;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.PlayerPreset;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.PlayerPresets;
import it.unicam.cs.mpgc.rpg119001.infrastructure.room.RoomTemplateDTO;
import it.unicam.cs.mpgc.rpg119001.infrastructure.room.RoomTemplateRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory responsible for creating complete game sessions.
 *
 * <p>This factory supports both the initialization of a new game and the
 * reconstruction of a previously saved game by assembling all required
 * domain objects.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Create a new game from a selected player preset.</li>
 *     <li>Restore a game session from saved data.</li>
 *     <li>Instantiate players, rooms and enemies.</li>
 *     <li>Reconstruct the game world from its persistent representation.</li>
 * </ul>
 */
public class GameFactory {

    private final RoomFactory roomFactory = new RoomFactory();
    private final RoomTemplateRepository roomTemplateRepository = new RoomTemplateRepository();
    private final EnemyFactory enemyFactory = new EnemyFactory();

    public Game createNewGame(PlayerPreset preset) {

        RoomTemplateDTO dto = roomTemplateRepository.randomTemplate();

        Room room = roomFactory.createNewRoom(GameConstants.START_GAME_LEVEL, dto);

        Player player = new Player(
                preset,
                room.getPlayerSpawnPosition()
        );

        return new Game(
                player,
                room,
                GameConstants.START_GAME_LEVEL
        );
    }

    public Game continueGame(SaveGame save) {

        RoomTemplateDTO dto = roomTemplateRepository.getById(save.getRoomTemplate());

        List<Entity> entities = new ArrayList<>();

        for (SaveGame.EnemyState enemyState : save.getEnemyStates()) {
            Enemy enemy = enemyFactory.createEnemyByArchetype(save.getLevel(), enemyState.archetype);
            enemy.setGridPosition(new GridPosition(enemyState.x, enemyState.y));
            enemy.setCurrentHealthPoints(enemyState.currentHp);
            entities.add(enemy);
        }

        Room room = roomFactory.loadRoom(dto, entities);

        //Save Game...so also continue game is allowed only when a room is completed
        room.destroyExitDoor();

        String playerArchetype = save.getPlayerState().archetype;
        PlayerPreset playerPreset = PlayerPresets.getByArchetype(playerArchetype);

        Player player = new Player(
                playerPreset,
                new GridPosition(save.getPlayerState().x, save.getPlayerState().y)
        );

        player.roadToLevel(save.getPlayerState().level, save.getPlayerState().exp);
        player.setCurrentHealthPoints(save.getPlayerState().currentHp);

        return new Game(player, room, save.getLevel());
    }
}