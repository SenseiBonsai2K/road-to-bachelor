package it.unicam.cs.mpgc.rpg119001.presentation.controller;

import it.unicam.cs.mpgc.rpg119001.application.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.application.service.CollisionService;
import it.unicam.cs.mpgc.rpg119001.application.service.GameFlowService;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.MovementService;
import it.unicam.cs.mpgc.rpg119001.application.service.PathfindingService;
import it.unicam.cs.mpgc.rpg119001.config.Constants.GridConstants;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.presentation.renderer.GameRenderer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.util.LinkedList;
import java.util.List;

public class GameController {

    private final SceneManager sceneManager;

    private final CollisionService collisionService;
    private final MovementService movementService;
    private final GameFlowService gameFlowService;

    private Game game;
    private GameRenderer gameRenderer;
    private boolean changingRoom = false;

    private List<GridPosition> currentPath = new LinkedList<>();

    private Entity selectedEnemy;

    @FXML private Label playerNameLabel;
    @FXML private ListView<String> playerStatsList;

    @FXML private Label selectedEnemyNameLabel;
    @FXML private ListView<String> selectedEnemyStatsList;

    @FXML private AnchorPane gamePane;

    public GameController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.collisionService = sceneManager.getCollisionService();
        this.movementService = sceneManager.getMovementService();
        this.gameFlowService = sceneManager.getGameFlowService();
    }

    @FXML
    private void initialize() {

        this.game = sceneManager.getCurrentGame();
        this.gameRenderer = new GameRenderer(gamePane);

        setupInput();
        startGameLoop();

        showPlayer();
        gamePane.requestFocus();
    }

    private void setupInput() {

        gamePane.setOnMouseClicked(event -> {

            double tileSize = GridConstants.TILE_SIZE;

            int tileX = (int) Math.floor(event.getX() / tileSize);
            int tileY = (int) Math.floor(event.getY() / tileSize);

            GridPosition clicked = new GridPosition(tileX, tileY);

            List<Entity> entities = game.getCurrentRoom().getEntitiesAt(clicked);

            if (!entities.isEmpty()) {

                selectedEnemy = entities.getFirst();
                if (selectedEnemy instanceof Enemy) {
                    showEnemy((Enemy) selectedEnemy);
                    return;
                }
            }

            GridPosition start = game.getPlayer().getGridPosition();

            currentPath = PathfindingService.findPath(
                    start,
                    clicked,
                    game.getCurrentRoom(),
                    collisionService
            );
        });
    }

    private void showPlayer() {
        Player player = game.getPlayer();
        playerNameLabel.setText(player.getDisplayName());
        playerStatsList.getItems().setAll(player.getStats());
    }

    private void showEnemy(Enemy enemy) {
        selectedEnemyNameLabel.setText(enemy.getDisplayName());
        selectedEnemyStatsList.getItems().setAll(enemy.getStats());
    }

    private void startGameLoop() {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                handleMovement();
                handleExit();
                gameRenderer.render(game);

                showPlayer();
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

        movementService.move(game.getPlayer(), target, game.getCurrentRoom());
    }

    private void handleExit() {

        if (changingRoom) return;

        Room currentRoom = game.getCurrentRoom();
        Player player = game.getPlayer();

        if (!player.getGridPosition().equals(currentRoom.getLeavePosition())) {
            return;
        }

        changingRoom = true;

        game.nextLevel();

        Room nextRoom = gameFlowService.nextRoom(game.getLevel());

        game.setCurrentRoom(nextRoom);

        player.setGridPosition(nextRoom.getPlayerSpawnPosition());

        currentPath.clear();

        changingRoom = false;
    }
}