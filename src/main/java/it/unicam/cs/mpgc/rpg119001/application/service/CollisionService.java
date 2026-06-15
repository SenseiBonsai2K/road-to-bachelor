package it.unicam.cs.mpgc.rpg119001.application.service;

import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;

public class CollisionService {

    public boolean canMoveTo(Room room, GridPosition target) {
        return room.isWalkable(target);
    }
}