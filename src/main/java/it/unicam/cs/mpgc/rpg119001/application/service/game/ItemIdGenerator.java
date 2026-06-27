package it.unicam.cs.mpgc.rpg119001.application.service.game;

/**
 * Utility class responsible for generating unique identifiers for in-game items.
 *
 * <p>Uses a global incremental counter combined with a prefix to ensure uniqueness.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Generate unique IDs for items.</li>
 * </ul>
 */
public class ItemIdGenerator {
    private static long counter = 0;

    public static String next(String prefix) {
        return prefix + "_" + (counter++);
    }
}
