package it.unicam.cs.mpgc.rpg119001.domain.entity.item;

public class Weapon extends Equipment {

    private final int attackBonus;
    private final int attackSpeed;
    private final int attackRange;

    public Weapon(String id, String name, String spritePath, int attackBonus, int attackSpeed, int attackRange
    ) {
        super(id, name, spritePath);
        this.attackBonus = attackBonus;
        this.attackSpeed = attackSpeed;
        this.attackRange = attackRange;
    }

    public int getAttackBonus() {
        return this.attackBonus;
    }

    public int getAttackSpeed() {
        return this.attackSpeed;
    }

    public int getAttackRange() {
        return this.attackRange;
    }
}
