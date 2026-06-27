package it.unicam.cs.mpgc.rpg119001.presentation.controller;

import it.unicam.cs.mpgc.rpg119001.application.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.application.service.character.EnemyAIService;
import it.unicam.cs.mpgc.rpg119001.application.service.combat.CombatService;
import it.unicam.cs.mpgc.rpg119001.application.service.game.GameFlowService;
import it.unicam.cs.mpgc.rpg119001.application.service.game.UIService;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.MovementService;
import it.unicam.cs.mpgc.rpg119001.application.service.character.PlayerActionService;
import it.unicam.cs.mpgc.rpg119001.application.service.character.PlayerCommandService;
import it.unicam.cs.mpgc.rpg119001.application.service.game.SaveGameMapper;
import it.unicam.cs.mpgc.rpg119001.application.service.game.SaveService;
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
    private final EnemyAIService enemyAIService;
    private UIService uiService;

    private Game game;
    private GameRenderer gameRenderer;
    private AnimationTimer gameLoop;

    @FXML private Label playerNameLabel;
    @FXML private ListView<String> playerStatsList;

    @FXML private Label selectedEnemyNameLabel;
    @FXML private ListView<String> selectedEnemyStatsList;

    @FXML private AnchorPane gamePane;

    @FXML private Button saveGameButton;
    @FXML private Button saveAndExitButton;
    @FXML private Button saveAndMainMenuButton;

    /**
     * JavaFX controller responsible for managing the main game scene.
     *
     * <p>This controller coordinates the interaction between the user interface
     * and the application services during gameplay. It initializes the game,
     * starts the main update loop, processes player input, updates the UI,
     * renders the game world and manages save/load related actions.</p>
     *
     * <h2>Responsibilities</h2>
     * <ul>
     *     <li>Initialize the gameplay scene.</li>
     *     <li>Handle player mouse input.</li>
     *     <li>Run the main game loop.</li>
     *     <li>Coordinate movement, combat and enemy AI.</li>
     *     <li>Update the graphical interface.</li>
     *     <li>Manage room transitions.</li>
     *     <li>Handle game over and save operations.</li>
     * </ul>
     */
    public GameController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.movementService = sceneManager.getMovementService();
        this.gameFlowService = sceneManager.getGameFlowService();
        this.saveService = sceneManager.getSaveService();
        this.saveGameMapper = sceneManager.getSaveGameMapper();
        this.combatService = sceneManager.getCombatService();
        this.playerActionService = sceneManager.getPlayerActionService();
        this.playerCommandService = sceneManager.getPlayerCommandService();
        this.enemyAIService = sceneManager.getEnemyAIService();
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
        combatService.setOnPlayerDeath(this::handleGameOver);
    }

    private void handleGameOver() {
        stopGameLoop();
        gamePane.setDisable(true);
        sceneManager.setCurrentGame(null);
        sceneManager.showGameOver();
    }

    private void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop();
            gameLoop = null;
        }
    }

    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateSaveButtonsVisibility();

                playerActionService.update(game, movementService, combatService);

                enemyAIService.updateEnemies(game);

                handleExit();

                gameRenderer.render(game);
                uiService.updatePlayer(game.getPlayer());
            }
        };
        gameLoop.start();
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

    private void updateSaveButtonsVisibility() {

        boolean visible = showSaveButtons();

        saveGameButton.setDisable(!visible);
        saveAndExitButton.setDisable(!visible);
        saveAndMainMenuButton.setDisable(!visible);
    }

    private boolean showSaveButtons() {
        Room currentRoom = game.getCurrentRoom();
        return currentRoom.getTileAt(currentRoom.getLeavePosition()).isWalkable();
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

    @FXML
    private void saveAndMainMenu() throws IOException {
        saveGame();
        stopGameLoop();
        gamePane.setDisable(true);
        sceneManager.setCurrentGame(null);
        sceneManager.showMainMenu();
    }
}