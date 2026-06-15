package it.unicam.cs.mpgc.rpg119001.presentation.renderer;

import it.unicam.cs.mpgc.rpg119001.config.Constants.GridConstants;
import it.unicam.cs.mpgc.rpg119001.domain.entity.Entity;
import it.unicam.cs.mpgc.rpg119001.domain.world.Tile;
import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.domain.world.Room;
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

        Room room = game.getCurrentRoom();

        renderTiles(room);
        renderPlayer(game);
        renderEntities(room);
    }

    public void clear() {
        gamePane.getChildren().clear();
    }

    private void renderTiles(Room room) {

        Tile[][] tiles = room.getTiles();

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {

                Tile tile = tiles[x][y];

                addTileSprite(tile, new GridPosition(x, y));
            }
        }
    }

    private void renderEntities(Room room) {

        Tile[][] tiles = room.getTiles();

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {

                for (Entity e : room.getEntitiesAt(new GridPosition(x, y))) {
                    addEntitySprite(e);
                }
            }
        }
    }

    private void renderPlayer(Game game) {
        addEntitySprite(game.getPlayer());
    }

    private void addTileSprite(Tile tile, GridPosition pos) {

        ImageView imageView = loadImageView(tile.getSpritePath());

        if (imageView == null) return;

        int tileSize = GridConstants.TILE_SIZE;

        imageView.setFitWidth(tileSize);
        imageView.setFitHeight(tileSize);

        imageView.setLayoutX(pos.toPixelX());
        imageView.setLayoutY(pos.toPixelY());

        gamePane.getChildren().add(imageView);
    }

    private void addEntitySprite(Entity entity) {

        ImageView imageView = loadImageView(entity.getSpritePath());

        if (imageView == null) return;

        int tileSize = GridConstants.TILE_SIZE;

        imageView.setFitWidth(tileSize);
        imageView.setFitHeight(tileSize);

        imageView.setLayoutX(entity.getGridPosition().toPixelX());
        imageView.setLayoutY(entity.getGridPosition().toPixelY());

        gamePane.getChildren().add(imageView);
    }

    private ImageView loadImageView(String spritePath) {

        try {
            ImageView imageView = new ImageView(getImage(spritePath));
            imageView.setPreserveRatio(true);

            return imageView;

        } catch (Exception e) {

            System.err.println("Failed to load sprite: " + spritePath);
            return null;
        }
    }

    private Image getImage(String path) {

        return imageCache.computeIfAbsent(
                path,
                Image::new
        );
    }
}