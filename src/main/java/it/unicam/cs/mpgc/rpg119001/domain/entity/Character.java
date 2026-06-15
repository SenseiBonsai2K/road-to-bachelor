package it.unicam.cs.mpgc.rpg119001.domain.entity;

import it.unicam.cs.mpgc.rpg119001.domain.movement.Movable;
import it.unicam.cs.mpgc.rpg119001.domain.movement.MovementState;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

public abstract class Character extends Entity implements Movable, BlockingEntity {
    private int healthPoints;
    private int attackPoints;
    private int speed;
    private final MovementState movementState = new MovementState();

    public Character(String id, int healthPoints, int attackPoints, GridPosition gridPosition, int speed, String spritePath) {
        super(id, gridPosition, spritePath);
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.speed = speed;
    }

    public int getAttackPoints() {return attackPoints;}
    public int getHealthPoints() {return healthPoints;}

    public void setHealthPoints(int healthPoints) {this.healthPoints = healthPoints;}
    public void setAttackPoints(int attackPoints) {this.attackPoints = attackPoints;}

    @Override
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public MovementState getMovementState() {
        return movementState;
    }
}
