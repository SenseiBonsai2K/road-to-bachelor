package it.unicam.cs.mpgc.rpg119001.game.config;

public class Constants {
    public static class MainMenuConstants {
        public static final String CONTINUE_BUTTON_TEXT = "Continue";
        public static final String NEW_GAME_BUTTON_TEXT = "New Game";
        public static final String SETTINGS_BUTTON_TEXT = "Settings";
        public static final String EXIT_BUTTON_TEXT = "Exit";
    }

    public static class PlayerSelectionConstants {
        public static final String NEXT_PLAYER_BUTTON_TEXT = "Next Player";
        public static final String START_GAME_BUTTON_TEXT = "Start Game";
    }

    public static class GameConstants {
        public static final String GAME_TITLE = "Road to Bachelor";
        public static final int START_GAME_LEVEL = 1;
        public static final int HEALTH_POINTS_PER_LEVEL = 10;
        public static final int ATTACK_POINTS_PER_LEVEL = 2;
    }

    public static class RoomConstants {
        public static final int ROOM_WIDTH = 600;
        public static final int ROOM_HEIGHT = 400;
    }

    public static class ViewPathConstants {
        public static final String MAIN_MENU_VIEW_PATH = "/view/main-menu-view.fxml";
        public static final String PLAYER_SELECTION_VIEW_PATH = "/view/player-selection-view.fxml";
        public static final String GAME_VIEW_PATH = "/view/game-view.fxml";
    }

    public static class PlayerImagePathConstants {
        public static final String WARRIOR = "/images/players/warrior.png";
        public static final String MAGE = "/images/players/mage.png";
    }

    public static class EnemyImagePathConstants {
        public static final String GOBLIN = "/images/enemies/goblin.png";
        public static final String ORC = "/images/enemies/orc.png";
    }
}
