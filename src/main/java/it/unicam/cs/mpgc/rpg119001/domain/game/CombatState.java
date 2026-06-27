package it.unicam.cs.mpgc.rpg119001.domain.game;

/**
 * Represents the combat state of a fightable entity.
 *
 * <p>This class stores timing information required to enforce attack
 * cooldowns, preventing entities from attacking more frequently than
 * their configured attack speed.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Track the timestamp of the last attack.</li>
 *     <li>Determine whether a new attack is allowed.</li>
 *     <li>Register successful attacks.</li>
 * </ul>
 */
public class CombatState {
    private long lastAttackTime;

    public boolean canAttack(long now, int attackSpeed) {
        return now - lastAttackTime >= attackSpeed;
    }

    public void registerAttack(long now) {
        lastAttackTime = now;
    }
}
