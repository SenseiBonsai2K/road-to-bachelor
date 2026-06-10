package it.unicam.cs.mpgc.rpg119001.controller;

import it.unicam.cs.mpgc.rpg119001.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.game.config.Constants.GameConstants;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import it.unicam.cs.mpgc.rpg119001.game.config.Constants.MainMenuConstants;
import javafx.scene.text.Text;

public class MainMenuController {

    @FXML
    private Text titleText;
    @FXML
    private Button continueButton;
    @FXML
    private Button newGameButton;
    @FXML
    private Button settingButton;
    @FXML
    private Button exitButton;

    private final SceneManager sceneManager;

    public MainMenuController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private void initialize() {
        updateUI();
    }

    private void updateUI() {
        titleText.setText(GameConstants.GAME_TITLE);
        continueButton.setText(MainMenuConstants.CONTINUE_BUTTON_TEXT);
        newGameButton.setText(MainMenuConstants.NEW_GAME_BUTTON_TEXT);
        settingButton.setText(MainMenuConstants.SETTINGS_BUTTON_TEXT);
        exitButton.setText(MainMenuConstants.EXIT_BUTTON_TEXT);
    }

    @FXML
    private void onNewGame(){
        System.out.println("Starting new game...");
        if (sceneManager != null) {
            sceneManager.showPlayerSelection();
        } else {
            System.out.println("SceneManager is not initialized.");
        }
    }

    @FXML
    private void onExit() {
        Platform.exit();
    }
}
