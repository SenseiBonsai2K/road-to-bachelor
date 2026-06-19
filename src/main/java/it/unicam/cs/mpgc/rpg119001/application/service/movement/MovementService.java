package it.unicam.cs.mpgc.rpg119001.application.service.movement;

import it.unicam.cs.mpgc.rpg119001.application.service.CollisionService;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.movement.Movable;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

public class MovementService {

    private final CollisionService collisionService;
    private final MovementStrategy movementStrategy;

    public MovementService(CollisionService collisionService,
                           MovementStrategy movementStrategy) {
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