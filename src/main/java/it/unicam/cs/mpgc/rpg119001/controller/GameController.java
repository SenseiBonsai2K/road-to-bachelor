package it.unicam.cs.mpgc.rpg119001.controller;

import it.unicam.cs.mpgc.rpg119001.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.game.core.Game;
import it.unicam.cs.mpgc.rpg119001.game.GameRenderer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class GameController {

    private final SceneManager sceneManager;
    private GameRenderer renderer;

    @FXML
    public Label playerHpLabel;
    @FXML
    public Label playerAttLabel;
    @FXML
    public Label enemyHpLabel;
    @FXML
    public Label enemyAttLabel;
    @FXML
    public AnchorPane gamePane;

    public GameController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private void initialize() {
        Game game = sceneManager.getCurrentGame();
        this.renderer = new GameRenderer(gamePane);
        renderer.render(game);
        updateUI(game);
    }

    private void updateUI(Game game) {
        playerHpLabel.setText("HP: " + game.getPlayer().getHealthPoints());
        playerAttLabel.setText("Att: " + game.getPlayer().getAttackPoints());
    }
}
