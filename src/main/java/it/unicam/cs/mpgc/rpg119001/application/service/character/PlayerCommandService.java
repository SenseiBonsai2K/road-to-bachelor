package it.unicam.cs.mpgc.rpg119001.application.service.character;

import it.unicam.cs.mpgc.rpg119001.application.service.game.UIService;
import it.unicam.cs.mpgc.rpg119001.application.service.combat.AttackPositionService;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.PathfindingService;
import it.unicam.cs.mpgc.rpg119001.config.Constants.GridConstants;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

import java.util.List;

/**
 * Service responsible for translating player input into executable game actions.
 *
 * <p>This service interprets mouse clicks performed by the player and
 * generates the corresponding movement or combat commands. Depending on
 * the clicked tile, it may compute a movement path or determine the
 * optimal attack position before delegating execution to
 * {@link PlayerActionService}.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Interpret player click events.</li>
 *     <li>Identify clicked entities.</li>
 *     <li>Compute movement paths.</li>
 *     <li>Queue movement and combat actions.</li>
 * </ul>
 */
public class PlayerCommandService {

    private final PathfindingService pathfindingService;
    private final AttackPositionService attackPositionService;

    private final PlayerActionService actionService;

    public PlayerCommandService(PathfindingService pathfindingService,
                                AttackPositionService attackPositionService,
                                PlayerActionService actionService) {
        this.pathfindingService = pathfindingService;
        this.attackPositionService = attackPositionService;
        this.actionService = actionService;
    }

    public void handleClick(double xClick, double yClick, Game game, UIService uiService) {

        GridPosition clickedPosition = toGridPosition(xClick, yClick);
        GridPosition destination = determineDestination(clickedPosition, game, uiService);

        List<GridPosition> path = pathfindingService.findPath(
                game.getPlayer().getGridPosition(),
                destination,
                game.getCurrentRoom());

        actionService.setPath(path);
    }

    private GridPosition toGridPosition(double x, double y) {

        double tileSize = GridConstants.TILE_SIZE;

        return new GridPosition(
                (int) Math.floor(x / tileSize),
                (int) Math.floor(y / tileSize)
        );
    }

    private GridPosition determineDestination(GridPosition clickedPosition, Game game, UIService uiService) {

        Room room = game.getCurrentRoom();

        Entity entity = room.getEntityAt(clickedPosition);

        if (!(entity instanceof Enemy enemy)) return clickedPosition;

        uiService.updateEnemy(enemy);

        actionService.setTarget(enemy);

        return attackPositionService.findBestAttackPosition(game.getPlayer(), enemy, room);
    }
}