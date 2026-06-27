package it.unicam.cs.mpgc.rpg119001.application.service.game;

/**
 * Utility class responsible for generating unique identifiers for game entities.
 *
 * <p>Identifiers are generated using a simple incremental counter combined
 * with a prefix to distinguish different entity types.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Generate unique IDs for entities.</li>
 * </ul>
 */
public class EntityIdGenerator {
    private static long counter = 0;

    public static String next(String prefix) {
        return prefix + "_" + (counter++);
    }
}
