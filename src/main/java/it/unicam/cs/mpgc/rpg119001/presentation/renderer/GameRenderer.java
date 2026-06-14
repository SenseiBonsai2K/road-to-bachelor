package it.unicam.cs.mpgc.rpg119001.presentation.renderer;

import it.unicam.cs.mpgc.rpg119001.config.Constants.GridConstants;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.entity.Character;
import it.unicam.cs.mpgc.rpg119001.domain.entity.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.SpriteEntity;
import it.unicam.cs.mpgc.rpg119001.domain.world.obstacle.Wall;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class GameRenderer {

    private final Pane gamePane;

    private final Map<String, Image> imageCache = new HashMap<>();

    public GameRenderer(Pane gamePane) {
        this.gamePane = gamePane;
    }

    public void render(Game game) {
        clear();

        //TODO walls has to be called only on room enter
        showWalls(game);

        showPlayer(game);
        showEnemies(game);
    }

    public void clear() {
        gamePane.getChildren().clear();
    }

    public void showPlayer(Game game) {
        Character player = game.getPlayer();
        addSprite(player);
    }

    public void showEnemies(Game game) {
        for (Enemy enemy : game.getCurrentRoom().getEnemies()) {
            addSprite(enemy);
        }
    }

    public void showWalls(Game game) {
        for (Object obstacle : game.getCurrentRoom().getObstacles()) {
            if (obstacle instanceof Wall wall) {
                addSprite(wall);
            }
        }
    }

    private void addSprite(SpriteEntity entity) {

        ImageView imageView = loadImageView(entity.getImagePath());

        if (imageView == null) {
            return;
        }

        int tileSize = GridConstants.TILE_SIZE;

        imageView.setFitWidth(tileSize);
        imageView.setFitHeight(tileSize);
        imageView.setLayoutX(entity.getGridPosition().toPixelX());
        imageView.setLayoutY(entity.getGridPosition().toPixelY());

        gamePane.getChildren().add(imageView);
    }

    private Image getImage(String path) {

        return imageCache.computeIfAbsent(
                path,
                Image::new
        );
    }

    private ImageView loadImageView(String imagePath) {

        try {
            ImageView imageView = new ImageView(getImage(imagePath));
            imageView.setPreserveRatio(true);

            return imageView;

        } catch (Exception e) {

            System.err.println("Failed to load image: " + imagePath);
            return null;
        }
    }
}
