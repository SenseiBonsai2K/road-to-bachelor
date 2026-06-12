package it.unicam.cs.mpgc.rpg119001.game.entity;

import it.unicam.cs.mpgc.rpg119001.game.world.GridPosition;

public abstract class Character implements SpriteEntity {
    private String name;
    private int healthPoints;
    private int attackPoints;
    private GridPosition gridPosition;
    private boolean isAlive;
    private double speed;
    private final String imagePath;

    public Character(int healthPoints, int attackPoints, GridPosition gridPosition, double speed, String imagePath) {
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.gridPosition = gridPosition;
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

    public void setGridPosition(GridPosition gridPosition) {
        this.gridPosition = gridPosition;
    }

    @Override
    public GridPosition getGridPosition() {
        return gridPosition;
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
