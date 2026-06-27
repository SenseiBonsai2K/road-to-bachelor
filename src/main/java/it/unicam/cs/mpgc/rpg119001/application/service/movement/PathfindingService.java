package it.unicam.cs.mpgc.rpg119001.application.service.movement;

import it.unicam.cs.mpgc.rpg119001.application.service.game.CollisionService;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

import java.util.*;

/**
 * Service responsible for computing movement paths between positions
 * within a room.
 *
 * <p>This service implements a breadth-first search (BFS) algorithm to
 * determine the shortest traversable path between two grid positions,
 * taking into account room boundaries, walkable tiles and entity collisions.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Compute the shortest traversable path between two positions.</li>
 *     <li>Respect collision and movement constraints.</li>
 *     <li>Reconstruct the resulting path from the search graph.</li>
 * </ul>
 */
public class PathfindingService {

    private final CollisionService collisionService = new CollisionService();

    public List<GridPosition> findPath(GridPosition start, GridPosition goal, Room room) {
        Queue<GridPosition> queue = new LinkedList<>();
        Map<GridPosition, GridPosition> cameFrom = new HashMap<>();
        Set<GridPosition> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            GridPosition current = queue.poll();

            if (current.equals(goal)) return rebuild(cameFrom, start, goal);

            for (GridPosition next : neighbors(current)) {
                if (visited.contains(next)) continue;
                if (!collisionService.canMoveTo(room, next, null)) continue;
                visited.add(next);
                cameFrom.put(next, current);
                queue.add(next);
            }
        }

        return List.of();
    }

    private List<GridPosition> rebuild(Map<GridPosition, GridPosition> cameFrom, GridPosition start, GridPosition goal) {
        LinkedList<GridPosition> path = new LinkedList<>();
        GridPosition current = goal;

        while (current != null && !current.equals(start)) {
            path.addFirst(current);
            current = cameFrom.get(current);
        }

        return path;
    }

    private List<GridPosition> neighbors(GridPosition p) {
        return List.of(
                p.translate(1, 0),
                p.translate(-1, 0),
                p.translate(0, 1),
                p.translate(0, -1)
        );
    }
}