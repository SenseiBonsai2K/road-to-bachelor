package it.unicam.cs.mpgc.rpg119001.application.service;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

import java.util.*;

public class PathfindingService {

    public static List<GridPosition> findPath(GridPosition start, GridPosition goal, Room room, CollisionService collisionService) {
        Queue<GridPosition> queue = new LinkedList<>();
        Map<GridPosition, GridPosition> cameFrom = new HashMap<>();
        Set<GridPosition> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            GridPosition current = queue.poll();
            if (current.equals(goal)) return rebuild(cameFrom, start, goal);

            for (GridPosition n : neighbors(current)) {
                if (visited.contains(n)) continue;
                if (!collisionService.canMoveTo(room, n)) continue;
                visited.add(n);
                cameFrom.put(n, current);
                queue.add(n);
            }
        }

        return new LinkedList<>();
    }

    private static List<GridPosition> rebuild(Map<GridPosition, GridPosition> cameFrom, GridPosition start, GridPosition goal) {

        LinkedList<GridPosition> path = new LinkedList<>();

        GridPosition current = goal;

        while (current != null && !current.equals(start)) {
            path.addFirst(current);
            current = cameFrom.get(current);
        }

        return path;
    }

    private static List<GridPosition> neighbors(GridPosition p) {
        return List.of(
                p.translate(1, 0),
                p.translate(-1, 0),
                p.translate(0, 1),
                p.translate(0, -1)
        );
    }
}
