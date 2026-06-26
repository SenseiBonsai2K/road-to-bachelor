package it.unicam.cs.mpgc.rpg119001.application.service.combat;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.domain.world.Tile;

/**
 * Service responsible for computing line-of-sight visibility between two positions in a room.
 *
 * <p>This service determines whether a target position is visible from a starting position,
 * considering obstacles such as tiles and entities that may block vision.</p>
 *
 * <p>The implementation supports only orthogonal visibility (horizontal and vertical lines),
 * and does not currently handle diagonal line-of-sight.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Determine if two positions are aligned (same row or column).</li>
 *     <li>Check if any tile blocks vision between two positions.</li>
 *     <li>Check if any entity blocks vision.</li>
 * </ul>
 */
public class LineOfSightService {

    public boolean hasLineOfSight(GridPosition start, GridPosition end, Room room) {

        if (!isAligned(start, end)) return false;

        return isClearPath(start, end, room);
    }

    private boolean isAligned(GridPosition startPosition, GridPosition endPosition) {
        return startPosition.getTileX() == endPosition.getTileX() ||
                startPosition.getTileY() == endPosition.getTileY();
    }

    private boolean isClearPath(GridPosition start, GridPosition end, Room room) {

        if (start.getTileX() == end.getTileX()) {
            return scan(start, end, room, true);
        }

        return scan(start, end, room, false);
    }

    private boolean scan(GridPosition start, GridPosition end, Room room, boolean vertical) {
        int dx = vertical ? 0 : (start.getTileX() < end.getTileX() ? 1 : -1);
        int dy = vertical ? (start.getTileY() < end.getTileY() ? 1 : -1) : 0;

        int x = start.getTileX() + dx;
        int y = start.getTileY() + dy;

        while (x != end.getTileX() || y != end.getTileY()) {

            if (blocksVision(new GridPosition(x, y), room)) {
                return false;
            }

            x += dx;
            y += dy;
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