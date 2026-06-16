package it.unicam.cs.mpgc.rpg119001.infrastructure.room;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Tile;
import it.unicam.cs.mpgc.rpg119001.domain.world.TileType;

import java.util.ArrayList;
import java.util.List;

public class RoomTemplateParser {

    public RoomTemplate parse(RoomTemplateDTO dto) {

        int height = dto.tiles().size();
        int width = dto.tiles().getFirst().length();

        for (String row : dto.tiles()) {
            if (row.length() != width) {
                throw new IllegalStateException("Broken map");
            }
        }

        Tile[][] tiles = new Tile[width][height];

        GridPosition playerSpawn = null;
        GridPosition entranceSpawn = null;
        GridPosition leaveSpawn = null;
        List<GridPosition> enemySpawns = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            String row = dto.tiles().get(y);

            for (int x = 0; x < width; x++) {
                char c = row.charAt(x);

                switch (c) {
                    case '#':
                        tiles[x][y] = new Tile(TileType.WALL);
                        break;

                    case '.':
                        tiles[x][y] = new Tile(TileType.FLOOR);
                        break;

                    case 'E':
                        tiles[x][y] = new Tile(TileType.FLOOR);
                        entranceSpawn = new GridPosition(x, y);
                        break;

                    case 'L':
                        tiles[x][y] = new Tile(TileType.FLOOR);
                        leaveSpawn = new GridPosition(x, y);
                        break;

                    case 'P':
                        tiles[x][y] = new Tile(TileType.FLOOR);
                        playerSpawn = new GridPosition(x, y);
                        break;

                    case 'S':
                        tiles[x][y] = new Tile(TileType.FLOOR);
                        enemySpawns.add(new GridPosition(x, y));
                        break;

                    default:
                        throw new IllegalArgumentException(
                                "Unknown tile: " + c
                        );
                }
            }
        }

        return new RoomTemplate(
                tiles,
                playerSpawn,
                enemySpawns,
                entranceSpawn,
                leaveSpawn
        );
    }
}