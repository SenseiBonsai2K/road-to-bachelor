package it.unicam.cs.mpgc.rpg119001.domain.game;

public class CombatState {
    private long lastAttackTime;

    public boolean canAttack(long now, int attackSpeed) {
        return now - lastAttackTime >= attackSpeed;
    }

    public void registerAttack(long now) {
        lastAttackTime = now;
    }
}
