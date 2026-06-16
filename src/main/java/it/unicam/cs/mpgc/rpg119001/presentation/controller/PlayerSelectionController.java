package it.unicam.cs.mpgc.rpg119001.presentation.controller;

import it.unicam.cs.mpgc.rpg119001.config.Constants.PlayerSelectionConstants;
import it.unicam.cs.mpgc.rpg119001.application.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.PlayerPreset;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.PlayerPresets;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.List;

public class PlayerSelectionController {

    @FXML
    public Text nameText;
    @FXML
    public ListView<String> statsListView;
    @FXML
    public ImageView playerImage;
    @FXML
    public Button nextPlayerButton;
    @FXML
    public Button startGameButton;

    private final SceneManager sceneManager;

    private final List<PlayerPreset> playerList = PlayerPresets.getAll();

    private int currentPlayerIndex = 0;

    public PlayerSelectionController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private void initialize() {
        showCurrentPlayer();
        nextPlayerButton.setText(PlayerSelectionConstants.NEXT_PLAYER_BUTTON_TEXT);
        startGameButton.setText(PlayerSelectionConstants.START_GAME_BUTTON_TEXT);
    }

    @FXML
    private void onNextPlayer() {
        currentPlayerIndex++;

        if (currentPlayerIndex >= playerList.size()) {
            currentPlayerIndex = 0;
        }

        showCurrentPlayer();
    }

    @FXML
    private void onStartGame() {
        PlayerPreset preset = getCurrentPreset();

        System.out.println("Selected player: " + preset.displayName());

        sceneManager.startGame(preset);
    }

    private PlayerPreset getCurrentPreset() {
        return playerList.get(currentPlayerIndex);
    }

    private void showCurrentPlayer() {
        PlayerPreset preset = getCurrentPreset();

        nameText.setText(preset.displayName());

        statsListView.getItems().setAll(
                "Archetype: " + preset.archetype(),
                "Health Points: " + preset.healthPoints(),
                "Attack Points: " + preset.attackPoints(),
                "Speed: " + preset.speed()
        );

        ImageView imageView = new ImageView(preset.spritePath());
        playerImage.setImage(imageView.getImage());
    }
}
