package it.unicam.cs.mpgc.rpg119001.game.entity;

import it.unicam.cs.mpgc.rpg119001.game.world.Position;

public abstract class Character {
    protected String name;
    protected int healthPoints;
    protected int attackPoints;
    protected Position position;
    protected boolean isAlive;
    protected double speed;

    public Character(int healthPoints, int attackPoints, Position position, double speed) {
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.position = position;
        this.speed = speed;
        isAlive = true;
    }
}
