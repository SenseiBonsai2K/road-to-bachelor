package it.unicam.cs.mpgc.rpg119001.controller;

import it.unicam.cs.mpgc.rpg119001.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.game.core.Game;
import it.unicam.cs.mpgc.rpg119001.game.entity.Enemy;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GameController {

    private final SceneManager sceneManager;

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
        System.out.println("GameController initialized with player: " + game.getPlayer().getName());
        System.out.println("Enemies: " + game.getCurrentRoom().getEnemies().size());
        System.out.println("Player: " + game.getPlayer().getName());
        renderGameScene(game);
        updateUI(game);
    }

    private void renderGameScene(Game game) {
        gamePane.getChildren().clear();
        
        spawnPlayer(game);
        spawnEnemies(game);
    }

    private void spawnPlayer(Game game) {
        String imagePath = game.getPlayer().getImagePath();
        ImageView playerImageView = loadCharacterImage(imagePath);
        
        if (playerImageView != null) {
            double x = game.getPlayer().getPosition().getX();
            double y = game.getPlayer().getPosition().getY();
            
            playerImageView.setLayoutX(x);
            playerImageView.setLayoutY(y);
            
            gamePane.getChildren().add(playerImageView);
        }
    }

    private void spawnEnemies(Game game) {
        for (Enemy enemy : game.getCurrentRoom().getEnemies()) {
            String imagePath = enemy.getImagePath();
            ImageView enemyImageView = loadCharacterImage(imagePath);
            
            if (enemyImageView != null) {
                double x = enemy.getPosition().getX();
                double y = enemy.getPosition().getY();
                
                enemyImageView.setLayoutX(x);
                enemyImageView.setLayoutY(y);
                
                gamePane.getChildren().add(enemyImageView);
            }
        }
    }

    private ImageView loadCharacterImage(String imagePath) {
        try {
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(32);
            imageView.setFitHeight(32);
            imageView.setPreserveRatio(true);
            return imageView;
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imagePath);
            return null;
        }
    }

    private void updateUI(Game game) {
        playerHpLabel.setText("HP: " + game.getPlayer().getHealthPoints());
        playerAttLabel.setText("Att: " + game.getPlayer().getAttackPoints());
    }
}
