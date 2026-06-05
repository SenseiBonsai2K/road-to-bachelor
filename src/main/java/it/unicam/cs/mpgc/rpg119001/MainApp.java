package it.unicam.cs.mpgc.rpg119001;

import it.unicam.cs.mpgc.rpg119001.util.Constants.GameConstants;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/game-view.fxml")
        );

        Scene scene = new Scene(loader.load(), GameConstants.WIDTH, GameConstants.HEIGHT);

        stage.setTitle(GameConstants.GAME_TITLE);
        stage.setScene(scene);
        stage.show();
    }

    static void main(String[] args) {
        launch(args);
    }
}