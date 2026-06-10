package it.unicam.cs.mpgc.rpg119001.manager;

import it.unicam.cs.mpgc.rpg119001.controller.GameController;
import it.unicam.cs.mpgc.rpg119001.controller.MainMenuController;
import it.unicam.cs.mpgc.rpg119001.controller.PlayerSelectionController;
import it.unicam.cs.mpgc.rpg119001.game.config.Constants;
import it.unicam.cs.mpgc.rpg119001.game.factory.GameFactory;
import it.unicam.cs.mpgc.rpg119001.game.core.Game;
import it.unicam.cs.mpgc.rpg119001.game.preset.PlayerPreset;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private final Stage stage;
    private Game currentGame;

    public SceneManager(Stage stage) {
        this.stage = stage;
        this.currentGame = null;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void showMainMenu() {
        loadScene(Constants.ViewPathConstants.MAIN_MENU_VIEW_PATH, MainMenuController.class);
    }

    public void showPlayerSelection() {
        loadScene(Constants.ViewPathConstants.PLAYER_SELECTION_VIEW_PATH, PlayerSelectionController.class);
    }

    public void showGame() {
        loadScene(Constants.ViewPathConstants.GAME_VIEW_PATH, GameController.class);
    }

    private <T> void loadScene(String fxml, Class<T> controllerClass) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

            loader.setControllerFactory(this::createController);

            Parent root = loader.load();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Object createController(Class<?> type) {

        if (type == MainMenuController.class) {
            return new MainMenuController(this);
        }

        if (type == PlayerSelectionController.class) {
            return new PlayerSelectionController(this);
        }

        if (type == GameController.class) {
            return new GameController(this);
        }

        throw new RuntimeException("Unknown controller: " + type);
    }

    public void startGame(PlayerPreset preset) {
        Game game = GameFactory.createNewGame(preset);
        setCurrentGame(game);
        showGame();
    }
}
