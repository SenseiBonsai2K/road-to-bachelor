package it.unicam.cs.mpgc.rpg119001.domain.entity.item;

/**
 * Represents a weapon that can be equipped by the player.
 *
 * <p>A weapon provides offensive bonuses by modifying attack damage,
 * attack speed and attack range.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Store attack damage bonuses.</li>
 *     <li>Store attack speed modifiers.</li>
 *     <li>Store attack range modifiers.</li>
 * </ul>
 *
 * <h2>Design Notes</h2>
 * <p>This class is a specialization of {@link Equipment} and only stores
 * weapon statistics. Applying these bonuses is delegated to gameplay
 * systems.</p>
 */
public class Weapon extends Equipment {

    private final int attackBonus;
    private final int attackSpeed;
    private final int attackRange;

    public Weapon(String id, String name, String spritePath, int attackBonus, int attackSpeed, int attackRange) {
        super(id, name, spritePath);
        this.attackBonus = attackBonus;
        this.attackSpeed = attackSpeed;
        this.attackRange = attackRange;
    }

    public int getAttackBonus() {
        return this.attackBonus;
    }

    public int getAttackSpeed() {
        return this.attackSpeed;
    }

    public int getAttackRange() {
        return this.attackRange;
    }
}
