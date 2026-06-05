package it.unicam.cs.mpgc.rpg119001.model;

/**
 * The Character class represents a generic character in the game.
 * It contains common attributes such as name, health points, attack points, and position, as well as methods for taking damage and healing.
 * This class serves as a base for specific character types like Player and Enemy, allowing for code reuse and a clear structure in the game's character hierarchy.
 * It is designed to be extended by more specific character classes, providing a common foundation for all characters in the game.
 * The Character class is abstract, meaning it cannot be instantiated directly, but it provides a template for creating specific character types with shared functionality.
 */
public abstract class Character {
    protected String name;
    protected int healthPoints;
    protected int attackPoints;
    protected Position position;
    protected boolean isAlive;

    public Character(String name, int healthPoints, int attackPoints, Position position) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        validatePosition(position);
        this.position = position;
        isAlive = true;
    }

    public void validatePosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null.");
        }
        if (position.getX() < 0 || position.getY() < 0) {
            throw new IllegalArgumentException("Position coordinates must be non-negative.");
        }
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void takeDamage(int damage) {
        this.healthPoints -= damage;
        if (this.healthPoints <= 0) {
            //TODO manage game over
            this.healthPoints = 0;
            isAlive = false;
        }
    }

    public void heal(int healAmount) {
        this.healthPoints += healAmount;
    }
}
