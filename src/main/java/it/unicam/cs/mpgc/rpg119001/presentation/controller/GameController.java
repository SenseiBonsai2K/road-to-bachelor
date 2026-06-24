package it.unicam.cs.mpgc.rpg119001.presentation.controller;

import it.unicam.cs.mpgc.rpg119001.application.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.application.service.*;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.MovementService;
import it.unicam.cs.mpgc.rpg119001.application.service.save.SaveGameMapper;
import it.unicam.cs.mpgc.rpg119001.application.service.save.SaveService;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.game.SaveGame;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
import it.unicam.cs.mpgc.rpg119001.presentation.renderer.GameRenderer;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GameController {

    private final SceneManager sceneManager;

    private final MovementService movementService;
    private final GameFlowService gameFlowService;
    private final SaveService saveService;
    private final SaveGameMapper saveGameMapper;
    private final CombatService combatService;
    private final PlayerActionService playerActionService;
    private final PlayerCommandService playerCommandService;
    private UIService uiService;

    private Game game;
    private GameRenderer gameRenderer;

    @FXML private Label playerNameLabel;
    @FXML private ListView<String> playerStatsList;

    @FXML private Label selectedEnemyNameLabel;
    @FXML private ListView<String> selectedEnemyStatsList;

    @FXML private AnchorPane gamePane;

    @FXML private Button saveGameButton;

    public GameController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.movementService = sceneManager.getMovementService();
        this.gameFlowService = sceneManager.getGameFlowService();
        this.saveService = sceneManager.getSaveService();
        this.saveGameMapper = sceneManager.getSaveGameMapper();
        this.combatService = sceneManager.getCombatService();
        this.playerActionService = sceneManager.getPlayerActionService();
        this.playerCommandService = sceneManager.getPlayerCommandService();
    }

    @FXML
    private void initialize() {

        game = sceneManager.getCurrentGame();
        gameRenderer = new GameRenderer(gamePane);

        this.uiService = new UIService(playerNameLabel,
                playerStatsList,
                selectedEnemyNameLabel,
                selectedEnemyStatsList);

        setupInput();
        setupCombatEvents();
        startGameLoop();

        uiService.updatePlayer(game.getPlayer());
        gamePane.requestFocus();
    }

    private void setupInput() {

        gamePane.setOnMouseClicked(event -> {
            playerCommandService.handleClick(
                    event.getX(),
                    event.getY(),
                    game,
                    uiService
            );
        });
    }

    private void setupCombatEvents() {
        combatService.setOnEnemyDeath(uiService::hideEnemy);
        combatService.setOnEnemyDamaged(enemy -> uiService.updateEnemy(enemy));
    }

    private void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerActionService.update(
                        game,
                        movementService,
                        combatService
                );

                handleExit();

                gameRenderer.render(game);
                uiService.updatePlayer(game.getPlayer());
            }
        }.start();
    }

    private void handleExit() {

        Room currentRoom = game.getCurrentRoom();
        Player player = game.getPlayer();

        if (!player.getGridPosition().equals(currentRoom.getLeavePosition())) {
            return;
        }

        gameFlowService.advanceToNextRoom(game);

        playerActionService.clear();
    }

    @FXML
    private void saveGame() throws IOException {
        SaveGame save = saveGameMapper.fromGame(game);
        saveService.save(save);
    }

    @FXML
    private void saveAndExit() throws IOException {
        saveGame();
        Platform.exit();
    }
}