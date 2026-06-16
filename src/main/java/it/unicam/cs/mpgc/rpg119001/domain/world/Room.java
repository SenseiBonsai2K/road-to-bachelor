package it.unicam.cs.mpgc.rpg119001.domain.world;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final Tile[][] tiles;
    private final GridPosition playerSpawnPosition;
    private final List<Entity>[][] entityGrid;
    private final GridPosition leaveSpawn;

    public Room(Tile[][] tiles, List<Entity> entities, GridPosition playerSpawn, GridPosition leaveSpawn) {
        this.tiles = tiles;
        this.playerSpawnPosition = playerSpawn;
        this.leaveSpawn = leaveSpawn;

        int width = tiles.length;
        int height = tiles[0].length;

        entityGrid = new ArrayList[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                entityGrid[x][y] = new ArrayList<>();
            }
        }

        for (Entity entity : entities) {
            GridPosition pos = entity.getGridPosition();
            entityGrid[pos.getTileX()][pos.getTileY()].add(entity);
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTileAt(GridPosition pos) {
        return tiles[pos.getTileX()][pos.getTileY()];
    }

    public List<Entity> getEntitiesAt(GridPosition pos) {
        return entityGrid[pos.getTileX()][pos.getTileY()];
    }

    public void addEntity(Entity entity) {
        GridPosition p = entity.getGridPosition();
        entityGrid[p.getTileX()][p.getTileY()].add(entity);
    }

    public void moveEntity(Entity entity, GridPosition newPos) {
        GridPosition oldPos = entity.getGridPosition();

        entityGrid[oldPos.getTileX()][oldPos.getTileY()].remove(entity);

        entity.setGridPosition(newPos);

        entityGrid[newPos.getTileX()][newPos.getTileY()].add(entity);
    }

    public GridPosition getPlayerSpawnPosition() {
        return playerSpawnPosition;
    }

    public void destroyExitDoor() {
       getTileAt(leaveSpawn).setType(TileType.FLOOR);
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }
}