package it.unicam.cs.mpgc.rpg119001.presentation.controller;

import it.unicam.cs.mpgc.rpg119001.application.manager.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;

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
