package it.unicam.cs.mpgc.rpg119001.domain.game;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;

public interface Fightable {

    int getAttackSpeed();
    int getAttackRange();
    int getAttackPoints();
    int getHealthPoints();
    int getCurrentHealthPoints();
    GridPosition getGridPosition();

    CombatState getCombatState();
    public void takeDamage(int damage);
    public boolean isDead();
}
