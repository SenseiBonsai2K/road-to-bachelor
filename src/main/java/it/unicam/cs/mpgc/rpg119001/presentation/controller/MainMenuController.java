package it.unicam.cs.mpgc.rpg119001.presentation.controller;

import it.unicam.cs.mpgc.rpg119001.application.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.config.Constants.GameConstants;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.game.SaveGame;
import it.unicam.cs.mpgc.rpg119001.infrastructure.factory.GameFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import it.unicam.cs.mpgc.rpg119001.config.Constants.MainMenuConstants;
import javafx.scene.text.Text;

/**
 * JavaFX controller responsible for the application's main menu.
 *
 * <p>This controller initializes the menu interface and manages
 * navigation to the different game states, including starting a new
 * game, continuing a saved game and exiting the application.</p>
 */
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
        continueButton.setDisable(!sceneManager.getSaveService().exists());
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
    private void onContinue() {

        try {
            SaveGame save = sceneManager.getSaveService().load();
            Game game = new GameFactory().continueGame(save);
            sceneManager.setCurrentGame(game);
            sceneManager.showGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onExit() {
        Platform.exit();
    }
}
