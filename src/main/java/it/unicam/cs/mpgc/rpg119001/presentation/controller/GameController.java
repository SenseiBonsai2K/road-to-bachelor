package it.unicam.cs.mpgc.rpg119001.presentation.controller;

import it.unicam.cs.mpgc.rpg119001.presentation.renderer.GameRenderer;
import it.unicam.cs.mpgc.rpg119001.presentation.input.InputMapper;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.application.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.application.service.MovementService;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private final SceneManager sceneManager;
    private final MovementService movementService;
    private final InputMapper inputMapper;
    private final List<KeyCode> pressedKeys = new ArrayList<>();
    private Game game;
    private GameRenderer gameRenderer;
    @FXML
    private Label playerHpLabel;
    @FXML
    private Label playerAttLabel;
    @FXML
    private Label enemyHpLabel;
    @FXML
    private Label enemyAttLabel;
    @FXML
    private AnchorPane gamePane;

    public GameController(SceneManager sceneManager, MovementService movementService, InputMapper inputMapper) {
        this.sceneManager = sceneManager;
        this.movementService = movementService;
        this.inputMapper = inputMapper;
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

        inputMapper.getDirection(pressedKeys)
                .ifPresent(direction ->
                        movementService.move(
                                game.getPlayer(),
                                direction,
                                game.getCurrentRoom()
                        ));
    }

    private void setupInput() {

        gamePane.setFocusTraversable(true);

        gamePane.setOnKeyPressed(event -> {
            pressedKeys.remove(event.getCode());
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
