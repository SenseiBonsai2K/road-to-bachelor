package it.unicam.cs.mpgc.rpg119001.controller;

import it.unicam.cs.mpgc.rpg119001.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.game.core.Game;
import javafx.fxml.FXML;

public class GameController {

    private final SceneManager sceneManager;

    public GameController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private void initialize() {
        Game game = sceneManager.getGameSession().getGame();
        System.out.println("GameController initialized with player: " + game.getPlayer().getName());
    }
}
