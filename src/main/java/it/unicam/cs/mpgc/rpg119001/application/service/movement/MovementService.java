package it.unicam.cs.mpgc.rpg119001.application.service.movement;

import it.unicam.cs.mpgc.rpg119001.application.service.game.CollisionService;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.movement.Movable;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

/**
 * Service responsible for executing entity movement within the game world.
 *
 * <p>This service coordinates movement validation, movement timing and
 * position updates by combining collision detection with a configurable
 * movement strategy.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Validate movement cooldowns.</li>
 *     <li>Compute the next movement step.</li>
 *     <li>Validate collisions before movement.</li>
 *     <li>Update entity positions inside a room.</li>
 * </ul>
 */
public class MovementService {

    private final CollisionService collisionService;
    private final MovementStrategy movementStrategy;

    public MovementService(CollisionService collisionService, MovementStrategy movementStrategy) {
        this.collisionService = collisionService;
        this.movementStrategy = movementStrategy;
    }

    public void move(Movable movable, GridPosition target, Room room) {

        long now = System.currentTimeMillis();

        if (!movable.getMovementState().canMove(now, movable.getSpeed())) return;

        GridPosition current = movable.getGridPosition();
        GridPosition next = movementStrategy.nextStep(current, target);

        if (!collisionService.canMoveTo(room, next, movable)) return;

        room.moveEntity((Entity) movable, next);

        movable.getMovementState().registerMove(now);
    }
}