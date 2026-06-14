package it.unicam.cs.mpgc.rpg119001.application.service;

import it.unicam.cs.mpgc.rpg119001.domain.movement.Movable;
import it.unicam.cs.mpgc.rpg119001.domain.world.Direction;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

public class MovementService {

    private final CollisionService collisionService;

    public MovementService(CollisionService collisionService) {
        this.collisionService = collisionService;
    }

    public void move(
            Movable entity,
            Direction direction,
            Room room) {

        long now = System.currentTimeMillis();

        if (!entity.getMovementState()
                .canMove(now, entity.getSpeed())) {
            return;
        }

        GridPosition target = entity.getGridPosition()
                .translate(direction.getDeltaX(), direction.getDeltaY());

        if (!collisionService.isWalkable(target, room)) {
            return;
        }

        entity.setGridPosition(target);
        entity.getMovementState().registerMove(now);

    }
}
