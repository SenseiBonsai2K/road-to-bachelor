package it.unicam.cs.mpgc.rpg119001.application.manager;

import it.unicam.cs.mpgc.rpg119001.presentation.controller.GameController;
import it.unicam.cs.mpgc.rpg119001.presentation.controller.MainMenuController;
import it.unicam.cs.mpgc.rpg119001.presentation.controller.PlayerSelectionController;
import it.unicam.cs.mpgc.rpg119001.config.Constants;
import it.unicam.cs.mpgc.rpg119001.infrastructure.factory.GameFactory;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.PlayerPreset;
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
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void showMainMenu() {
        loadScene(Constants.ViewPathConstants.MAIN_MENU_VIEW_PATH);
    }

    public void showPlayerSelection() {
        loadScene(Constants.ViewPathConstants.PLAYER_SELECTION_VIEW_PATH);
    }

    public void showGame() {
        loadScene(Constants.ViewPathConstants.GAME_VIEW_PATH);
    }

    private <T> void loadScene(String fxml) {
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

        if (type == MainMenuController.class)
            return new MainMenuController(this);

        if (type == PlayerSelectionController.class)
            return new PlayerSelectionController(this);

        if (type == GameController.class)
            return new GameController(this);

        throw new RuntimeException("Unknown controller: " + type);
    }

    public void startGame(PlayerPreset preset) {
        Game game = new GameFactory().createNewGame(preset);
        setCurrentGame(game);
        showGame();
    }
}
