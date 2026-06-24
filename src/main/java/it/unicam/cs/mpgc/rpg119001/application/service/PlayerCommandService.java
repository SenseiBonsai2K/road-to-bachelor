package it.unicam.cs.mpgc.rpg119001.application.service;

import it.unicam.cs.mpgc.rpg119001.application.service.movement.PathfindingService;
import it.unicam.cs.mpgc.rpg119001.config.Constants.GridConstants;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;


import java.util.List;

public class PlayerCommandService {

    private final PathfindingService pathfindingService;
    private final AttackPositionService attackPositionService;
    private final CollisionService collisionService;

    private final PlayerActionService actionService;

    public PlayerCommandService(PathfindingService pathfindingService,
                                AttackPositionService attackPositionService,
                                CollisionService collisionService,
                                PlayerActionService actionService) {
        this.pathfindingService = pathfindingService;
        this.attackPositionService = attackPositionService;
        this.collisionService = collisionService;
        this.actionService = actionService;
    }

    public void handleClick(double x, double y, Game game, UIService uiService) {

        double tileSize = GridConstants.TILE_SIZE;

        int tileX = (int) Math.floor(x / tileSize);
        int tileY = (int) Math.floor(y / tileSize);

        GridPosition end = new GridPosition(tileX, tileY);

        Player player = game.getPlayer();
        Room room = game.getCurrentRoom();

        Entity entity = room.getEntityAt(end);

        if (entity instanceof Enemy enemy) {
            uiService.updateEnemy(enemy);
            actionService.setTarget(enemy);
            end = attackPositionService.findBestAttackPosition(player, enemy, room);
        }

        List<GridPosition> path = pathfindingService.findPath(player.getGridPosition(), end, room, collisionService);

        actionService.setPath(path);
    }
}