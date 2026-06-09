package it.unicam.cs.mpgc.rpg119001.game.entity;

import it.unicam.cs.mpgc.rpg119001.game.world.Position;

public abstract class Character {
    private String name;
    private int healthPoints;
    private int attackPoints;
    private Position position;
    private boolean isAlive;
    private double speed;
    private final String imagePath;

    public Character(int healthPoints, int attackPoints, Position position, double speed, String imagePath) {
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.position = position;
        this.speed = speed;
        this.imagePath = imagePath;
        this.isAlive = true;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setPosition(Position playerSpawnPosition) {
        this.position = playerSpawnPosition;
    }

    public Position getPosition() {
        return position;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
