package it.unicam.cs.mpgc.rpg119001.domain.movement;

/**
 * Represents the movement state of a movable entity.
 *
 * <p>This class stores timing information required to enforce movement
 * cooldowns, ensuring that entities cannot move faster than their
 * configured movement speed.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Track the timestamp of the last movement.</li>
 *     <li>Determine whether movement is currently allowed.</li>
 *     <li>Register successful movement actions.</li>
 * </ul>
 */
public class MovementState {
    private long lastMoveTime;

    public boolean canMove(long now, int speed) {
        return now - lastMoveTime >= speed;
    }

    public void registerMove(long now) {
        lastMoveTime = now;
    }
}
