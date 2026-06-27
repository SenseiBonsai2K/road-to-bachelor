package it.unicam.cs.mpgc.rpg119001.domain.entity.item;

/**
 * Represents a piece of armor that can be equipped by the player.
 *
 * <p>An armor provides defensive bonuses while potentially reducing
 * movement speed. Different armor types can define different trade-offs
 * between protection and mobility.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Store defense-related bonuses.</li>
 *     <li>Store health bonuses.</li>
 *     <li>Store movement speed penalties.</li>
 * </ul>
 *
 * <h2>Design Notes</h2>
 * <p>This class is a specialization of {@link Equipment} and only stores
 * item statistics. Applying these bonuses is delegated to gameplay systems.</p>
 */
public class Armor extends Equipment {

    private final int defenseBonus;
    private final int healthBonus;
    private final int speedMalus;

    public Armor(String id, String name, String spritePath, int defenseBonus, int healthBonus, int speedMalus) {
        super(id, name, spritePath);
        this.defenseBonus = defenseBonus;
        this.healthBonus = healthBonus;
        this.speedMalus = speedMalus;
    }

    public int getDefenseBonus() {
        return this.defenseBonus;
    }

    public int getHealthBonus() {
        return this.healthBonus;
    }

    public int getSpeedMalus() {
        return this.speedMalus;
    }
}
