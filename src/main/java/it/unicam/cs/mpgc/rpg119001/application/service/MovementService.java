package it.unicam.cs.mpgc.rpg119001.application.service;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.movement.Movable;
import it.unicam.cs.mpgc.rpg119001.domain.world.Direction;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

public class MovementService {

    private final CollisionService collisionService;

    public MovementService(CollisionService collisionService) {
        this.collisionService = collisionService;
    }

    public void move(Movable movable, Direction direction, Room room) {

        long now = System.currentTimeMillis();

        if (!movable.getMovementState().canMove(now, movable.getSpeed())) {
            return;
        }

        GridPosition current = movable.getGridPosition();

        GridPosition target = switch (direction) {
            case UP -> current.translate(0, -1);
            case DOWN -> current.translate(0, 1);
            case LEFT -> current.translate(-1, 0);
            case RIGHT -> current.translate(1, 0);
        };

        if (!collisionService.canMoveTo(room, target)) {
            return;
        }

        room.moveEntity((Entity) movable, target);

        movable.getMovementState().registerMove(now);
    }
}