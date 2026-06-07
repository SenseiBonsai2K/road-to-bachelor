package it.unicam.cs.mpgc.rpg119001.controller;

import it.unicam.cs.mpgc.rpg119001.util.Constants;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import it.unicam.cs.mpgc.rpg119001.util.Constants.MainMenuConstants;
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

    @FXML
    private void initialize() {
        titleText.setText(Constants.GAME_TITLE);
        continueButton.setText(MainMenuConstants.CONTINUE_BUTTON_TEXT);
        newGameButton.setText(MainMenuConstants.NEW_GAME_BUTTON_TEXT);
        settingButton.setText(MainMenuConstants.SETTINGS_BUTTON_TEXT);
        exitButton.setText(MainMenuConstants.EXIT_BUTTON_TEXT);
    }

    @FXML
    private void onNewGame() {
        System.out.println("Starting new game...");
        // TODO:
        // Game game = GameFactory.createNewGame();
        // SceneManager.showGame(game);
    }

    @FXML
    private void onExit() {

        Platform.exit();
    }
}
