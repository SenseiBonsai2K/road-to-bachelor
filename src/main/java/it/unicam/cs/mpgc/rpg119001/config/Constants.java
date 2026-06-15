package it.unicam.cs.mpgc.rpg119001.config;

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
        public static final int DIFFICULTY = 2; // 1 = Easy, 2 = Normal, 3 = Hard
    }

    public static class GridConstants {
        public static final int TILE_SIZE = 16;
        public static final int ROOM_WIDTH_PX = 656;  // 41 tiles * 16px
        public static final int ROOM_HEIGHT_PX = 464; // 29 tiles * 16px
        public static final int ROOM_TILES_WIDTH = ROOM_WIDTH_PX / TILE_SIZE;  // 41 tiles
        public static final int ROOM_TILES_HEIGHT = ROOM_HEIGHT_PX / TILE_SIZE; // 29 tiles
        public static final int WALL_THICKNESS_TILES = 1; // 1 tile wall border
    }

    public static class ViewPathConstants {
        public static final String MAIN_MENU_VIEW_PATH = "/view/main-menu-view.fxml";
        public static final String PLAYER_SELECTION_VIEW_PATH = "/view/player-selection-view.fxml";
        public static final String GAME_VIEW_PATH = "/view/game-view.fxml";
    }

    public static class PlayerSpritePathConstants {
        public static final String WARRIOR = "/sprite/player/warrior.png";
        public static final String MAGE = "/sprite/player/mage.png";
    }

    public static class EnemySpritePathConstants {
        public static final String GOBLIN = "/sprite/enemy/goblin.png";
        public static final String ORC = "/sprite/enemy/orc.png";
    }

    public static class TileSpritePathConstants {
        public static final String WALL = "/sprite/world/tile/wall.png";
        public static final String FLOOR = "/sprite/world/tile/floor.png";
    }

    public static class ArmorSpritePathConstants {
        public static final String LEATHER_ARMOR = "/sprite/world/item/armor/leather-armor.png";
        public static final String HEAVY_ARMOR = "/sprite/world/item/armor/heavy-armor.png";
    }

    public static class WeaponSpritePathConstants {
        public static final String LONG_SWORD = "/sprite/world/item/weapon/long-sword.png";
        public static final String WOOD_STAFF = "/sprite/world/item/weapon/wood-staff.png";
    }

    public static class ConsumableSpritePathConstants {
        public static final String HEAL_POTION = "/sprite/world/item/consumable/heal-potion.png";
        public static final String SPEED_POTION = "/sprite/world/item/consumable/speed-potion.png";
    }
}
