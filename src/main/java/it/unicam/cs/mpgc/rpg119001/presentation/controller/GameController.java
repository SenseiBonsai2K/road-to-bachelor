package it.unicam.cs.mpgc.rpg119001.presentation.controller;

import it.unicam.cs.mpgc.rpg119001.application.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.application.service.CollisionService;
import it.unicam.cs.mpgc.rpg119001.application.service.MovementService;
import it.unicam.cs.mpgc.rpg119001.application.service.PathfindingService;
import it.unicam.cs.mpgc.rpg119001.config.Constants.GridConstants;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.presentation.renderer.GameRenderer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.LinkedList;
import java.util.List;

public class GameController {

    private final SceneManager sceneManager;

    private final CollisionService collisionService = new CollisionService();
    private final MovementService movementService = new MovementService(collisionService);

    private Game game;
    private GameRenderer gameRenderer;

    private List<GridPosition> currentPath = new LinkedList<>();

    @FXML private Label playerHpLabel;
    @FXML private Label playerAttLabel;
    @FXML private AnchorPane gamePane;

    public GameController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private void initialize() {

        this.game = sceneManager.getCurrentGame();
        this.gameRenderer = new GameRenderer(gamePane);

        setupInput();
        startGameLoop();

        gamePane.requestFocus();
    }

    private void setupInput() {

        gamePane.setOnMouseClicked(event -> {

            double tileSize = GridConstants.TILE_SIZE;

            int tileX = (int) Math.floor(event.getX() / tileSize);
            int tileY = (int) Math.floor(event.getY() / tileSize);

            GridPosition start = game.getPlayer().getGridPosition();
            GridPosition goal = new GridPosition(tileX, tileY);

            currentPath = PathfindingService.findPath(start, goal, game.getCurrentRoom(), collisionService);
        });
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

        if (currentPath == null || currentPath.isEmpty()) return;

        GridPosition playerPos = game.getPlayer().getGridPosition();
        GridPosition target = currentPath.getFirst();

        if (playerPos.equals(target)) {
            currentPath.removeFirst();
            return;
        }

        movementService.moveTowards(game.getPlayer(), target, game.getCurrentRoom());
    }
}