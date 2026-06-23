package it.unicam.cs.mpgc.rpg119001.application.service.save;

import it.unicam.cs.mpgc.rpg119001.domain.game.Game;
import it.unicam.cs.mpgc.rpg119001.domain.game.SaveGame;

import java.util.List;

public class SaveGameMapper {

    public SaveGame fromGame(Game game) {

        SaveGame.PlayerState playerState = new SaveGame.PlayerState();

        playerState.archetype = game.getPlayer().getArchetype();
        playerState.currentHp = game.getPlayer().getCurrentHealthPoints();
        playerState.exp = game.getPlayer().getExperience();
        playerState.level = game.getPlayer().getLevel();
        playerState.x = game.getPlayer().getGridPosition().getTileX();
        playerState.y = game.getPlayer().getGridPosition().getTileY();

        List<SaveGame.EnemyState> enemies =
                game.getCurrentRoom().extractEnemyState();

        return new SaveGame(
                game.getLevel(),
                game.getCurrentRoom().getRoomTemplateId(),
                playerState,
                enemies
        );
    }
}
