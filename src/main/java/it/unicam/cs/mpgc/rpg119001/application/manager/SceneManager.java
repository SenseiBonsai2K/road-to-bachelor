package it.unicam.cs.mpgc.rpg119001.application.manager;

import it.unicam.cs.mpgc.rpg119001.application.service.combat.AttackPositionService;
import it.unicam.cs.mpgc.rpg119001.application.service.combat.CombatService;
import it.unicam.cs.mpgc.rpg119001.application.service.combat.LineOfSightService;
import it.unicam.cs.mpgc.rpg119001.application.service.combat.RangeService;
import it.unicam.cs.mpgc.rpg119001.application.service.game.CollisionService;
import it.unicam.cs.mpgc.rpg119001.application.service.game.GameFlowService;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.MovementService;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.MovementStrategy;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.OrthogonalMovementStrategy;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.PathfindingService;
import it.unicam.cs.mpgc.rpg119001.application.service.player.PlayerActionService;
import it.unicam.cs.mpgc.rpg119001.application.service.player.PlayerCommandService;
import it.unicam.cs.mpgc.rpg119001.application.service.save.SaveGameMapper;
import it.unicam.cs.mpgc.rpg119001.application.service.save.SaveService;
import it.unicam.cs.mpgc.rpg119001.infrastructure.factory.RoomFactory;
import it.unicam.cs.mpgc.rpg119001.infrastructure.room.RoomTemplateRepository;
import it.unicam.cs.mpgc.rpg119001.presentation.controller.GameController;
import it.unicam.cs.mpgc.rpg119001.presentation.controller.MainMenuController;
import it.unicam.cs.mpgc.rpg119001.presentation.controller.PlayerSelectionController;
import it.unicam.cs.mpgc.rpg119001.config.Constants;
import it.unicam.cs.mpgc.rpg119001.infrastructure.factory.GameFactory;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.PlayerPreset;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private final Stage stage;
    private Game currentGame;

    private final RoomFactory roomFactory;
    private final RoomTemplateRepository roomTemplateRepository;

    private final CollisionService collisionService;
    private final MovementService movementService;
    private final MovementStrategy movementStrategy;
    private final GameFlowService gameFlowService;
    private final PathfindingService pathfindingService;
    private final SaveService saveService;
    private final SaveGameMapper saveGameMapper;
    private final RangeService rangeService;
    private final LineOfSightService lineOfSightService;
    private final AttackPositionService attackPositionService;
    private final CombatService combatService;
    private final PlayerActionService playerActionService;
    private final PlayerCommandService playerCommandService;

    public SceneManager(Stage stage) {
        this.stage = stage;

        this.roomFactory = new RoomFactory();
        this.roomTemplateRepository = new RoomTemplateRepository();

        this.collisionService = new CollisionService();
        this.movementStrategy = new OrthogonalMovementStrategy();
        this.pathfindingService = new PathfindingService();
        this.saveGameMapper = new SaveGameMapper();
        this.saveService = new SaveService();
        this.rangeService = new RangeService();
        this.lineOfSightService = new LineOfSightService();
        this.movementService = new MovementService(collisionService, movementStrategy);
        this.gameFlowService = new GameFlowService(roomFactory, roomTemplateRepository);
        this.attackPositionService = new AttackPositionService(rangeService, lineOfSightService, pathfindingService, collisionService);
        this.combatService = new CombatService(rangeService, lineOfSightService);
        this.playerActionService = new PlayerActionService();
        this.playerCommandService = new PlayerCommandService(pathfindingService, attackPositionService, collisionService, playerActionService);
    }

    public void setCurrentGame(Game currentGame) {this.currentGame = currentGame;}

    public Game getCurrentGame() {return currentGame;}
    public RoomFactory getRoomFactory() {return roomFactory;}
    public RoomTemplateRepository getRoomTemplateRepository() {return roomTemplateRepository;}
    public GameFlowService getGameFlowService() {return gameFlowService;}
    public MovementService getMovementService() {return movementService;}
    public CollisionService getCollisionService() {return collisionService;}
    public MovementStrategy getMovementStrategy() {return movementStrategy;}
    public PathfindingService getPathfindingService() {return pathfindingService;}
    public SaveGameMapper getSaveGameMapper() {return saveGameMapper;}
    public SaveService getSaveService() {return saveService;}
    public LineOfSightService getLineOfSightService() {return lineOfSightService;}
    public RangeService getRangeService() {return rangeService;}
    public AttackPositionService getAttackPositionService() {return attackPositionService;}
    public CombatService getCombatService() {return combatService;}
    public PlayerActionService getPlayerActionService() {return playerActionService;}
    public PlayerCommandService getPlayerCommandService() {return playerCommandService;}

    public void showMainMenu() {
        loadScene(Constants.ViewPathConstants.MAIN_MENU_VIEW_PATH);
    }

    public void showPlayerSelection() {
        loadScene(Constants.ViewPathConstants.PLAYER_SELECTION_VIEW_PATH);
    }

    public void showGame() {
        loadScene(Constants.ViewPathConstants.GAME_VIEW_PATH);
    }

    private <T> void loadScene(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            loader.setControllerFactory(this::createController);

            Parent root = loader.load();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Object createController(Class<?> type) {

        if (type == MainMenuController.class)
            return new MainMenuController(this);

        if (type == PlayerSelectionController.class)
            return new PlayerSelectionController(this);

        if (type == GameController.class)
            return new GameController(this);

        throw new RuntimeException("Unknown controller: " + type);
    }

    public void startGame(PlayerPreset preset) {
        Game game = new GameFactory().createNewGame(preset);
        setCurrentGame(game);
        showGame();
    }
}
