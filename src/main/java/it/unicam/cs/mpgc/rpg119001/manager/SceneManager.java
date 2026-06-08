package it.unicam.cs.mpgc.rpg119001.manager;

import it.unicam.cs.mpgc.rpg119001.controller.GameController;
import it.unicam.cs.mpgc.rpg119001.controller.MainMenuController;
import it.unicam.cs.mpgc.rpg119001.controller.PlayerSelectionController;
import it.unicam.cs.mpgc.rpg119001.game.factory.GameFactory;
import it.unicam.cs.mpgc.rpg119001.game.core.Game;
import it.unicam.cs.mpgc.rpg119001.game.session.GameSession;
import it.unicam.cs.mpgc.rpg119001.game.preset.PlayerPreset;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private final Stage stage;
    private GameSession gameSession;

    public SceneManager(Stage stage) {
        this.stage = stage;
        this.gameSession = new GameSession();
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public void showMainMenu() {
        loadScene("/view/main-menu-view.fxml", MainMenuController.class);
    }

    public void showPlayerSelection() {
        loadScene("/view/player-selection-view.fxml", PlayerSelectionController.class);
    }

    public void showGame() {
        loadScene("/view/game-view.fxml", GameController.class);
    }

    private <T> void loadScene(String fxml, Class<T> controllerClass) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

            loader.setControllerFactory(type -> {

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
            });

            Parent root = loader.load();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void startGame(PlayerPreset preset) {
        Game game = GameFactory.createNewGame(preset);
        gameSession.setGame(game);
        showGame();
    }
}
