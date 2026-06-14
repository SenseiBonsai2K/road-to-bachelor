package it.unicam.cs.mpgc.rpg119001.domain.entity;

import it.unicam.cs.mpgc.rpg119001.domain.movement.Movable;
import it.unicam.cs.mpgc.rpg119001.domain.movement.MovementState;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

public abstract class Character implements SpriteEntity, Movable {
    private int healthPoints;
    private int attackPoints;
    private GridPosition gridPosition;
    private boolean isAlive;
    private int speed;
    private final String imagePath;
    private final MovementState movementState = new MovementState();

    public Character(int healthPoints, int attackPoints, GridPosition gridPosition, int speed, String imagePath) {
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

    @Override
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

    @Override
    public MovementState getMovementState() {
        return movementState;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
