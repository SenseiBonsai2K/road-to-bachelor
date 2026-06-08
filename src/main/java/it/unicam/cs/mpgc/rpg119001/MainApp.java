package it.unicam.cs.mpgc.rpg119001;

import it.unicam.cs.mpgc.rpg119001.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.game.config.Constants.GameConstants;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage){
        SceneManager sceneManager = new SceneManager(primaryStage);

        primaryStage.setTitle(GameConstants.GAME_TITLE);
        sceneManager.showMainMenu();
    }

    static void main(String[] args) {
        launch(args);
    }
}