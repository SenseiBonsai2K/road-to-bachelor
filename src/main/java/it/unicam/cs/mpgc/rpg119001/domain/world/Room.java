package it.unicam.cs.mpgc.rpg119001.domain.world;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.game.SaveGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a playable room within the game world.
 *
 * <p>A room contains the tile map, entity placement, player spawn position,
 * exit location and all information required to simulate gameplay inside
 * a single level.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Store the room tile layout.</li>
 *     <li>Manage entity placement and movement.</li>
 *     <li>Provide access to tiles and entities.</li>
 *     <li>Maintain room-specific gameplay state.</li>
 *     <li>Extract runtime data for game persistence.</li>
 * </ul>
 */
public class Room {

    private final Tile[][] tiles;
    private final GridPosition playerSpawnPosition;
    private final Entity[][] entityGrid;
    private final GridPosition leaveSpawn;
    private final String roomTemplateId;

    public Room(String roomTemplateId, Tile[][] tiles, List<Entity> entities, GridPosition playerSpawn, GridPosition leaveSpawn) {
        this.tiles = tiles;
        this.playerSpawnPosition = playerSpawn;
        this.leaveSpawn = leaveSpawn;
        this.roomTemplateId = roomTemplateId;

        int width = tiles.length;
        int height = tiles[0].length;

        entityGrid = new Entity[width][height];

        for (Entity entity : entities) {
            GridPosition pos = entity.getGridPosition();
            entityGrid[pos.getTileX()][pos.getTileY()] = entity;
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public String getRoomTemplateId() {
        return roomTemplateId;
    }

    public Tile getTileAt(GridPosition pos) {
        return tiles[pos.getTileX()][pos.getTileY()];
    }

    public Entity getEntityAt(GridPosition pos) {
        return entityGrid[pos.getTileX()][pos.getTileY()];
    }

    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<>();

        for (int x = 0; x < getWidth(); x++) {
            entities.addAll(Arrays.asList(entityGrid[x]).subList(0, getHeight()));
        }

        return entities;
    }

    public void addEntity(Entity entity) {
        GridPosition pos = entity.getGridPosition();

        if (entityGrid[pos.getTileX()][pos.getTileY()] != null) {
            throw new IllegalStateException(
                    "Tile already occupied at (" +
                            pos.getTileX() + "," +
                            pos.getTileY() + ")"
            );
        }

        entityGrid[pos.getTileX()][pos.getTileY()] = entity;
    }

    public void removeEntity(Entity entity) {
        GridPosition pos = entity.getGridPosition();

        if (entityGrid[pos.getTileX()][pos.getTileY()] == entity) {
            entityGrid[pos.getTileX()][pos.getTileY()] = null;
        }
    }

    public void moveEntity(Entity entity, GridPosition newPos) {

        if (entityGrid[newPos.getTileX()][newPos.getTileY()] != null) {
            throw new IllegalStateException(
                    "Destination tile already occupied at (" +
                            newPos.getTileX() + "," +
                            newPos.getTileY() + ")"
            );
        }

        GridPosition oldPos = entity.getGridPosition();

        entityGrid[oldPos.getTileX()][oldPos.getTileY()] = null;

        entity.setGridPosition(newPos);

        entityGrid[newPos.getTileX()][newPos.getTileY()] = entity;
    }

    public GridPosition getPlayerSpawnPosition() {
        return playerSpawnPosition;
    }

    public void destroyExitDoor() {
       getTileAt(leaveSpawn).setType(TileType.FLOOR);
    }

    public GridPosition getLeavePosition() {
        return this.leaveSpawn;
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

    public List<SaveGame.EnemyState> extractEnemyState() {

        List<SaveGame.EnemyState> list = new ArrayList<>();

        for (Entity e : getEntities()) {
            if (e instanceof Enemy enemy) {
                SaveGame.EnemyState enemyState = new SaveGame.EnemyState();
                enemyState.archetype = enemy.getArchetype();
                enemyState.x = enemy.getGridPosition().getTileX();
                enemyState.y = enemy.getGridPosition().getTileY();
                enemyState.currentHp = enemy.getCurrentHealthPoints();
                list.add(enemyState);
            }
        }

        return list;
    }
}