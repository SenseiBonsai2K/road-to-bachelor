package it.unicam.cs.mpgc.rpg119001.application.manager;

import it.unicam.cs.mpgc.rpg119001.application.service.CollisionService;
import it.unicam.cs.mpgc.rpg119001.application.service.GameFlowService;
import it.unicam.cs.mpgc.rpg119001.application.service.PathfindingService;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.MovementService;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.MovementStrategy;
import it.unicam.cs.mpgc.rpg119001.application.service.movement.OrthogonalMovementStrategy;
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

    public SceneManager(Stage stage) {
        this.stage = stage;

        this.roomFactory = new RoomFactory();
        this.roomTemplateRepository = new RoomTemplateRepository();

        this.gameFlowService = new GameFlowService(roomFactory, roomTemplateRepository);
        this.collisionService = new CollisionService();
        this.movementStrategy = new OrthogonalMovementStrategy();
        this.pathfindingService = new PathfindingService();
        this.movementService = new MovementService(collisionService, movementStrategy);
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
