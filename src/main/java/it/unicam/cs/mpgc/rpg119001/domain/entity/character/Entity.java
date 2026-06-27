package it.unicam.cs.mpgc.rpg119001.domain.entity.character;

import it.unicam.cs.mpgc.rpg119001.domain.interaction.BlockingEntity;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

/**
 * Represents the base abstraction for every object placed in the game world.
 *
 * <p>An entity has a unique identifier, occupies a position on the grid
 * and owns a sprite used for rendering. Concrete subclasses define their
 * interaction behavior within the world.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Provide a unique identifier.</li>
 *     <li>Maintain the entity position.</li>
 *     <li>Expose the associated sprite resource.</li>
 * </ul>
 *
 * <h2>Design Notes</h2>
 * <p>This class serves as the root of the entity hierarchy, allowing
 * different game objects to share common properties while specializing
 * their behavior through inheritance.</p>
 */
public abstract class Entity implements BlockingEntity {
    private final String id;
    private GridPosition position;
    private final String spritePath;

    public Entity(String id, GridPosition position, String spritePath) {
        this.id = id;
        this.position = position;
        this.spritePath = spritePath;
    }

    public String getId() {
        return id;
    }

    public GridPosition getGridPosition() {
        return position;
    }

    public void setGridPosition(GridPosition position) {
        this.position = position;
    }

    public String getSpritePath() {
        return this.spritePath;
    }
}
