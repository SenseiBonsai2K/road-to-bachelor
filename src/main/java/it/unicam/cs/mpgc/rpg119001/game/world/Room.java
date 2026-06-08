package it.unicam.cs.mpgc.rpg119001.game.world;

import it.unicam.cs.mpgc.rpg119001.game.entity.Player;

public interface Room {

        void enter(Player player);

        void exit(Player player);

        boolean isCompleted();

}