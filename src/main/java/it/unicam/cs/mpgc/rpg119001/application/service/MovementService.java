package it.unicam.cs.mpgc.rpg119001.application.service;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.movement.Movable;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

public class MovementService {

    private final CollisionService collisionService;

    public MovementService(CollisionService collisionService) {
        this.collisionService = collisionService;
    }

    public void moveTowards(Movable movable, GridPosition target, Room room) {

        long now = System.currentTimeMillis();

        if (!movable.getMovementState().canMove(now, movable.getSpeed())) return;

        GridPosition current = movable.getGridPosition();

        int dx = Integer.compare(target.getTileX(), current.getTileX());
        int dy = Integer.compare(target.getTileY(), current.getTileY());

        if (dx != 0 && dy != 0) dy = 0;

        GridPosition next = current.translate(dx, dy);

        if (!collisionService.canMoveTo(room, next)) return;

        room.moveEntity((Entity) movable, next);

        movable.getMovementState().registerMove(now);
    }
}