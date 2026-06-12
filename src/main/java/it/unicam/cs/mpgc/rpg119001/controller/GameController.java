package it.unicam.cs.mpgc.rpg119001.controller;

import it.unicam.cs.mpgc.rpg119001.game.world.Direction;
import it.unicam.cs.mpgc.rpg119001.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.game.core.Game;
import it.unicam.cs.mpgc.rpg119001.game.GameRenderer;
import it.unicam.cs.mpgc.rpg119001.service.MovementService;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.util.HashSet;
import java.util.Set;

public class GameController {

    private final SceneManager sceneManager;
    private final MovementService movementService;

    private Game game;
    private GameRenderer gameRenderer;
    private final Set<KeyCode> pressedKeys = new HashSet<>();

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

    public GameController(SceneManager sceneManager, MovementService movementService) {
        this.sceneManager = sceneManager;
        this.movementService = movementService;
    }

    @FXML
    private void initialize() {
        this.game = sceneManager.getCurrentGame();
        this.gameRenderer = new GameRenderer(gamePane);

        //TODO this should be called when the player get damaged or request/on update of some enemy info
        this.updateUI(this.game);

        setupInput();
        gamePane.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {

        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                handleMovement();
                gameRenderer.render(game);
            }
        };

        timer.start();
    }

    private void handleMovement() {

        if (pressedKeys.contains(KeyCode.W)) {
            movementService.move(game.getPlayer(), Direction.UP, game.getCurrentRoom());
        } else if (pressedKeys.contains(KeyCode.S)) {
            movementService.move(game.getPlayer(), Direction.DOWN, game.getCurrentRoom());
        } else if (pressedKeys.contains(KeyCode.A)) {
            movementService.move(game.getPlayer(), Direction.LEFT, game.getCurrentRoom());
        } else if (pressedKeys.contains(KeyCode.D)) {
            movementService.move(game.getPlayer(), Direction.RIGHT, game.getCurrentRoom());
        }
    }

    private void setupInput() {

        gamePane.setFocusTraversable(true);

        gamePane.setOnKeyPressed(event -> {
            pressedKeys.add(event.getCode());
        });

        gamePane.setOnKeyReleased(event -> {
            pressedKeys.remove(event.getCode());
        });
    }

    private void updateUI(Game game) {
        playerHpLabel.setText("HP: " + game.getPlayer().getHealthPoints());
        playerAttLabel.setText("Att: " + game.getPlayer().getAttackPoints());
    }
}
