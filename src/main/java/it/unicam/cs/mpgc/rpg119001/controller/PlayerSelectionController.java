package it.unicam.cs.mpgc.rpg119001.controller;

import it.unicam.cs.mpgc.rpg119001.manager.SceneManager;
import it.unicam.cs.mpgc.rpg119001.game.preset.PlayerPreset;
import it.unicam.cs.mpgc.rpg119001.game.preset.PlayerPresets;
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
                "Health Points: " + preset.baseHealthPoints(),
                "Attack Points: " + preset.baseAttackPoints(),
                "Speed: " + preset.baseSpeed()
        );
    }
}
