package it.unicam.cs.mpgc.rpg119001.application.service;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.interaction.BlockingEntity;
import it.unicam.cs.mpgc.rpg119001.domain.movement.Movable;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.domain.world.Tile;

public class CollisionService {

    public boolean canMoveTo(Room room, GridPosition pos, Movable mover) {

        if (!isInside(room, pos)) {return false;}

        if (!room.getTileAt(pos).isWalkable()) {return false;}

        Entity entity = room.getEntityAt(pos);

        return entity == null || entity == mover || !entity.blocksMovement();
    }

    public boolean isInside(Room room, GridPosition pos) {
        return pos.getTileX() >= 0 &&
                pos.getTileY() >= 0 &&
                pos.getTileX() < room.getWidth() &&
                pos.getTileY() < room.getHeight();
    }
}