package it.unicam.cs.mpgc.rpg119001.domain.entity.character;

import it.unicam.cs.mpgc.rpg119001.domain.interaction.BlockingEntity;
import it.unicam.cs.mpgc.rpg119001.domain.movement.Movable;
import it.unicam.cs.mpgc.rpg119001.domain.movement.MovementState;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

public abstract class Character extends Entity implements Movable, BlockingEntity {
    private int healthPoints;
    private int attackPoints;
    private int speed;
    private int attackRange;
    private int attackSpeed;
    private final MovementState movementState = new MovementState();

    public Character(String id, int healthPoints, int attackPoints, int attackRange, GridPosition gridPosition, int speed, int attackSpeed, String spritePath) {
        super(id, gridPosition, spritePath);
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.speed = speed;
        this.attackSpeed = attackSpeed;
        this.attackRange = attackRange;
    }

    public int getAttackPoints() {return attackPoints;}
    public int getHealthPoints() {return healthPoints;}
    public int getAttackRange() {return this.attackRange;}
    public int getAttackSpeed() {return this.attackSpeed;}

    public void setHealthPoints(int healthPoints) {this.healthPoints = healthPoints;}
    public void setAttackPoints(int attackPoints) {this.attackPoints = attackPoints;}
    public void setAttackRange(int attackRange) {this.attackRange = attackRange;}
    public void setAttackSpeed(int attackSpeed) {this.attackSpeed = attackSpeed;}

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
