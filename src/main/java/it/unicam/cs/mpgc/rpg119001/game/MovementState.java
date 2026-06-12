package it.unicam.cs.mpgc.rpg119001.game;

public class MovementState {
    private long lastMoveTime;

    public boolean canMove(long now, int speed) {
        return now - lastMoveTime >= speed;
    }

    public void registerMove(long now) {
        lastMoveTime = now;
    }
}
