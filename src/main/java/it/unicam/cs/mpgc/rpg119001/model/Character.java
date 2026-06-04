package it.unicam.cs.mpgc.rpg119001.model;

public abstract class Character {
    protected String name;
    protected int healthPoints;
    protected int attackPoints;

    public Character(String name, int healthPoints, int attackPoints) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
    }

        public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void takeDamage(int damage) {
        this.healthPoints -= damage;
        if (this.healthPoints <= 0) {
            //TODO manage game over
            this.healthPoints = 0;
        }
    }

    public void heal(int healAmount) {
        this.healthPoints += healAmount;
    }
}
