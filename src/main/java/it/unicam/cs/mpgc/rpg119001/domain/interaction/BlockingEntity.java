package it.unicam.cs.mpgc.rpg119001.domain.interaction;

/**
 * Defines the contract for entities that interact with movement and
 * visibility within the game world.
 *
 * <p>Implementations specify whether they prevent movement and whether
 * they obstruct line of sight, allowing collision and combat systems
 * to evaluate environmental interactions consistently.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Specify movement blocking behavior.</li>
 *     <li>Specify line-of-sight blocking behavior.</li>
 * </ul>
 */
public interface BlockingEntity {
    boolean blocksMovement();
    boolean blocksLineOfSight();
}
