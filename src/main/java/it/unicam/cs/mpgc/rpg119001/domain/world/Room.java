package it.unicam.cs.mpgc.rpg119001.domain.world;

import it.unicam.cs.mpgc.rpg119001.domain.interaction.BlockingEntity;
import it.unicam.cs.mpgc.rpg119001.domain.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final Tile[][] tiles;
    private final GridPosition playerSpawnPosition;
    private final List<Entity>[][] entityGrid;

    public Room(Tile[][] tiles, List<Entity> entities, GridPosition playerSpawnPosition) {
        this.tiles = tiles;
        this.playerSpawnPosition = playerSpawnPosition;

        int width = tiles.length;
        int height = tiles[0].length;

        entityGrid = new ArrayList[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                entityGrid[x][y] = new ArrayList<>();
            }
        }

        for (Entity e : entities) {
            addEntity(e);
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTile(GridPosition pos) {
        if (!isInside(pos)) return null;
        return tiles[pos.getTileX()][pos.getTileY()];
    }

    public List<Entity> getEntitiesAt(GridPosition pos) {
        return entityGrid[pos.getTileX()][pos.getTileY()];
    }

    public void addEntity(Entity entity) {
        GridPosition position = entity.getGridPosition();
        entityGrid[position.getTileX()][position.getTileY()].add(entity);
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

    private boolean isInside(GridPosition position) {
        return position.getTileX() >= 0 &&
                position.getTileY() >= 0 &&
                position.getTileX() < tiles.length &&
                position.getTileY() < tiles[0].length;
    }

    public boolean isWalkable(GridPosition position) {

        if (!isInside(position)) return false;

        Tile tile = getTile(position);

        if (!tile.isWalkable()) return false;

        for (Entity entity : getEntitiesAt(position)) {
            if (entity instanceof BlockingEntity blocking && blocking.blocksMovement()) return false;
        }

        return true;
    }
}
