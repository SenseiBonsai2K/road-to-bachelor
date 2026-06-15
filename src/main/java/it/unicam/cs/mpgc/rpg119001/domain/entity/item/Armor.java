package it.unicam.cs.mpgc.rpg119001.domain.entity.item;

public class Armor extends Equipment {

    private final int defenseBonus;
    private final int healthBonus;
    private final int speedMalus;

    public Armor(String id, String name, String spritePath, int defenseBonus, int healthBonus, int speedMalus) {
        super(id, name, spritePath);
        this.defenseBonus = defenseBonus;
        this.healthBonus = healthBonus;
        this.speedMalus = speedMalus;
    }

    public int getDefenseBonus() {
        return this.defenseBonus;
    }

    public int getHealthBonus() {
        return this.healthBonus;
    }

    public int getSpeedMalus() {
        return this.speedMalus;
    }
}
