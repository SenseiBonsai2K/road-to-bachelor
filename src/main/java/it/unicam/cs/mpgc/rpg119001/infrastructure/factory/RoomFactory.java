package it.unicam.cs.mpgc.rpg119001.infrastructure.factory;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.infrastructure.room.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory responsible for creating room instances from room templates.
 *
 * <p>This factory converts serialized room templates into runtime room
 * objects, spawning enemies and initializing the room state required by
 * the game.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Create new rooms from room templates.</li>
 *     <li>Spawn enemies for newly generated rooms.</li>
 *     <li>Reconstruct rooms when loading saved games.</li>
 *     <li>Initialize room entities and tile layout.</li>
 * </ul>
 */
public class RoomFactory {

    private final EnemyFactory enemyFactory = new EnemyFactory();
    private final RoomTemplateParser parser = new RoomTemplateParser();

    public Room createNewRoom(int level, RoomTemplateDTO dto) {

        List<Entity> entities = new ArrayList<>();

        RoomTemplate template = parser.parse(dto);

        for (GridPosition spawn : template.enemySpawns()) {
            Enemy enemy = enemyFactory.createRandomEnemy(level);
            enemy.setGridPosition(spawn);
            entities.add(enemy);
        }

        return new Room(dto.id(), template.tiles(), entities, template.playerSpawn(), template.leaveSpawn());
    }

    public Room loadRoom(RoomTemplateDTO dto, List<Entity> entities) {

        RoomTemplate template = parser.parse(dto);

        return new Room(dto.id(), template.tiles(), entities, template.playerSpawn(), template.leaveSpawn());
    }
}
