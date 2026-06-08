package it.unicam.cs.mpgc.rpg119001.game.config;

import it.unicam.cs.mpgc.rpg119001.game.world.Position;

public class Constants {
    public static final String GAME_TITLE = "Road to Bachelor";
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    public static class MainMenuConstants {
        public static final String CONTINUE_BUTTON_TEXT = "Continue";
        public static final String NEW_GAME_BUTTON_TEXT = "New Game";
        public static final String SETTINGS_BUTTON_TEXT = "Settings";
        public static final String EXIT_BUTTON_TEXT = "Exit";
    }

    public static class GameConstants {
        public static final Position PLAYER_START_POSITION = new Position(0, HEIGHT/2);
    }
}
