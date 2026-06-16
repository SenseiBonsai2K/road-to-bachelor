package it.unicam.cs.mpgc.rpg119001.application.service;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.interaction.BlockingEntity;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.domain.world.Tile;

public class CollisionService {

    public boolean canMoveTo(Room room, GridPosition pos, Entity mover) {

        if (!isInside(room, pos)) return false;

        Tile tile = room.getTileAt(pos);
        if (!tile.isWalkable()) return false;

        for (Entity entity : room.getEntitiesAt(pos)) {
            if (entity == mover) continue;
            if (entity instanceof BlockingEntity b && b.blocksMovement()) return false;
        }

        return true;
    }

    public boolean isInside(Room room, GridPosition pos) {
        return pos.getTileX() >= 0 &&
                pos.getTileY() >= 0 &&
                pos.getTileX() < room.getWidth() &&
                pos.getTileY() < room.getHeight();
    }
}