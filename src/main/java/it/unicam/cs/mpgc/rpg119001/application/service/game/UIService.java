package it.unicam.cs.mpgc.rpg119001.application.service.game;

import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Enemy;
import it.unicam.cs.mpgc.rpg119001.domain.entity.character.Player;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class UIService {
    private final Label playerNameLabel;
    private final ListView<String> playerStatsList;

    private final Label enemyNameLabel;
    private final ListView<String> enemyStatsList;

    public UIService(Label playerNameLabel,
                     ListView<String> playerStatsList,
                     Label enemyNameLabel,
                     ListView<String> enemyStatsList) {

        this.playerNameLabel = playerNameLabel;
        this.playerStatsList = playerStatsList;
        this.enemyNameLabel = enemyNameLabel;
        this.enemyStatsList = enemyStatsList;
    }

    public void updatePlayer(Player player) {
        playerNameLabel.setText(player.getDisplayName());
        playerStatsList.getItems().setAll(player.getStats());
    }

    public void updateEnemy(Enemy enemy) {
        if (enemy.isDead()) {
            hideEnemy();
            return;
        }
        enemyNameLabel.setText(enemy.getDisplayName());
        enemyStatsList.getItems().setAll(enemy.getStats());
    }

    public void hideEnemy() {
        enemyNameLabel.setText("");
        enemyStatsList.getItems().clear();
    }
}
