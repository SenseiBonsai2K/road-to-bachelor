package it.unicam.cs.mpgc.rpg119001.domain.entity.character;

import it.unicam.cs.mpgc.rpg119001.domain.game.SaveGame;
import it.unicam.cs.mpgc.rpg119001.domain.world.GridPosition;
import it.unicam.cs.mpgc.rpg119001.infrastructure.preset.character.PlayerPreset;
import it.unicam.cs.mpgc.rpg119001.config.Constants.PlayerGameConstants;

import java.util.List;

import static it.unicam.cs.mpgc.rpg119001.config.Constants.GameConstants.MS_PER_SECOND;

public class Player extends Character {

    private final PlayerPreset preset;

    private int level;
    private int experience;
    private int experienceCap;

    public Player(PlayerPreset preset, GridPosition gridPosition) {
        super(
                preset.id(),
                preset.currentHealthPoints(),
                preset.healthPoints(),
                preset.attackPoints(),
                preset.attackRange(),
                gridPosition,
                preset.speed(),
                preset.attackSpeed(),
                preset.spritePath()
        );
        this.level = preset.level();
        this.experience = preset.experience();
        this.experienceCap = preset.experienceCap();
        this.preset = preset;
    }

    public String getArchetype() {return this.preset.archetype();}
    public String getDisplayName() {return this.preset.displayName();}
    public int getLevel() {return this.level;}
    public int getExperience() {return this.experience;}
    public int getExperienceCap() {return this.experienceCap;}

    public void levelUp() {
        this.level++;
        this.setHealthPoints(this.getHealthPoints()+PlayerGameConstants.HEALTH_POINTS_PER_LEVEL);
        this.setAttackPoints(this.getAttackPoints()+PlayerGameConstants.ATTACK_POINTS_PER_LEVEL);
        nextExperienceCap();
    }

    public void addExperience(int experienceGained) {
        this.experience += experienceGained;

        while (this.experience >= this.experienceCap) {
            this.experience -= this.experienceCap;
            levelUp();
        }
    }

    public void nextExperienceCap() {
        this.experienceCap = this.experienceCap + (this.experienceCap / 2);
    }

    @Override
    public boolean blocksMovement(){
        return true;
    }
    @Override
    public boolean blocksLineOfSight(){
        return true;
    }

    public List<String> getStats() {
        return List.of(
                "Level: "+getLevel(),
                "Exp: "+getExperience()+" / "+getExperienceCap(),
                "Health: "+this.getCurrentHealthPoints()+" / "+this.getHealthPoints(),
                "Attack: "+this.getAttackPoints(),
                "Range: "+this.getAttackRange(),
                "Speed: " + String.format("%.2f", MS_PER_SECOND / this.getSpeed()) + " tiles/s",
                "Atk Speed: " + String.format("%.2f", MS_PER_SECOND / this.getAttackSpeed()) + " hit/s"
        );
    }

    public SaveGame.PlayerState toState() {
        SaveGame.PlayerState playerState = new SaveGame.PlayerState();
        playerState.archetype = this.getArchetype();
        playerState.exp = this.getExperience();
        playerState.currentHp = this.getCurrentHealthPoints();
        playerState.level = this.getLevel();
        playerState.x = this.getGridPosition().getTileX();
        playerState.y = this.getGridPosition().getTileY();
        return playerState;
    }

    public void roadToLevel(int level, int exp) {
        for (int i = this.getLevel(); i <= level; i++) {
            this.levelUp();
        }
        this.addExperience(exp);
    }
}
