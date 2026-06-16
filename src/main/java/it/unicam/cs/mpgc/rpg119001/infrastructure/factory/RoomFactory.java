package it.unicam.cs.mpgc.rpg119001.infrastructure.factory;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.infrastructure.room.*;

import java.util.ArrayList;
import java.util.List;

public class RoomFactory {

    private final EnemyFactory enemyFactory = new EnemyFactory();
    private final RoomTemplateParser roomTemplateParser = new RoomTemplateParser();
    private final RoomTemplateRepository roomTemplateRepository = new RoomTemplateRepository();

    public Room createRoom(int level) {

        RoomTemplateDTO dto = roomTemplateRepository.randomTemplate();
        RoomTemplate template = roomTemplateParser.parse(dto);

        List<Entity> enemies = new ArrayList<>();

        for (GridPosition spawnPosition : template.enemySpawns()) {
            Enemy enemy = enemyFactory.createEnemy(level);
            enemy.setGridPosition(spawnPosition);
            enemies.add(enemy);
        }

        return new Room(template.tiles(), enemies, template.playerSpawn(), template.leaveSpawn());
    }
}