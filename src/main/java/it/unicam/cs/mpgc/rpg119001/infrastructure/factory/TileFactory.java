package it.unicam.cs.mpgc.rpg119001.infrastructure.factory;

import it.unicam.cs.mpgc.rpg119001.config.Constants.GridConstants;
import it.unicam.cs.mpgc.rpg119001.domain.world.Tile;
import it.unicam.cs.mpgc.rpg119001.domain.world.TileType;

public class TileFactory {

    public static Tile[][] createBaseRoom() {

        int width = GridConstants.ROOM_TILES_WIDTH;
        int height = GridConstants.ROOM_TILES_HEIGHT;

        Tile[][] tiles = new Tile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                boolean isBorder =
                        x == 0 || y == 0 ||
                                x == width - 1 || y == height - 1;

                tiles[x][y] = new Tile(
                        isBorder ? TileType.WALL : TileType.FLOOR
                );
            }
        }

        return tiles;
    }
}

