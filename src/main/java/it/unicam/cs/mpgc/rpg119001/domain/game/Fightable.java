package it.unicam.cs.mpgc.rpg119001.domain.game;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

/**
 * Defines the contract for any entity that can participate in combat.
 *
 * <p>Implementations of this interface represent game objects that can
 * attack, receive damage and die, such as players and enemies.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Expose combat statistics (attack, range, speed, health).</li>
 *     <li>Expose current position in the game world.</li>
 *     <li>Provide access to combat state (cooldowns, timing).</li>
 *     <li>Support receiving damage and death evaluation.</li>
 * </ul>
 */
public interface Fightable {

    int getAttackSpeed();
    int getAttackRange();
    int getAttackPoints();
    int getHealthPoints();
    int getCurrentHealthPoints();
    GridPosition getGridPosition();

    CombatState getCombatState();
    void takeDamage(int damage);
    boolean isDead();
}
