package it.unicam.cs.mpgc.rpg119001.presentation.controller;

import it.unicam.cs.mpgc.rpg119001.application.manager.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * JavaFX controller responsible for the Game Over screen.
 *
 * <p>This controller allows the player to either return to the
 * main menu or terminate the application after the game ends.</p>
 */
public class GameOverController {

    private final SceneManager sceneManager;

    public GameOverController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private void showMainMenu() {
        sceneManager.setCurrentGame(null);
        sceneManager.showMainMenu();
    }

    @FXML
    private void exitGame() {
        Platform.exit();
    }
}
