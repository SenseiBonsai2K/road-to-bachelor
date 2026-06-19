package it.unicam.cs.mpgc.rpg119001.domain.entity;

public class ItemIdGenerator {
    private static long counter = 0;

    public static String next(String prefix) {
        return prefix + "_" + (counter++);
    }
}
