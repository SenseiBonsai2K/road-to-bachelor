package it.unicam.cs.mpgc.rpg119001.application.service.combat;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.domain.world.Tile;

public class LineOfSightService {

    public boolean hasLineOfSight(GridPosition start,
                                  GridPosition end,
                                  Room room) {

        int startX = start.getTileX();
        int startY = start.getTileY();

        int endX = end.getTileX();
        int endY = end.getTileY();

        if (startX != endX && startY != endY) {
            return false;
        }

        if (startX == endX) {

            int step = startY < endY ? 1 : -1;

            for (int y = startY + step; y != endY; y += step) {

                GridPosition current = new GridPosition(startX, y);

                if (blocksVision(current, room)) {
                    return false;
                }
            }

            return true;
        }

        int step = startX < endX ? 1 : -1;

        for (int x = startX + step; x != endX; x += step) {

            GridPosition current = new GridPosition(x, startY);

            if (blocksVision(current, room)) {
                return false;
            }
        }

        return true;
    }

    private boolean blocksVision(GridPosition position, Room room) {

        Tile tile = room.getTileAt(position);

        if (tile.blocksLineOfSight()) {
            return true;
        }

        Entity entity = room.getEntityAt(position);

        return entity != null && entity.blocksLineOfSight();
    }
}