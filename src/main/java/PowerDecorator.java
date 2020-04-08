package main.java;

import static java.lang.Math.floor;

public class PowerDecorator extends CapeDecorator {
    /**
     * A cape decorator thing.
     * @param impDam    The damage value to add to the current one.
     * @param impDodge  The dodge value to add to the current one.
     * @param impArm    The armor value to add to the current one.
     * @param impHealth The health value to add to the current one.
     */
    public PowerDecorator(Person cape, int impDam, int impDodge, int impArm, int impHealth) {
        super(cape);
        cape.setDamage(impDam + cape.getDamage());
        cape.setDodge(impDodge + cape.getDodge());
        cape.setArmor(impArm + cape.getArmor());
        cape.setMaxHealth(impHealth  + cape.getMaxHealth());
        cape.setCurrentHealth(cape.getMaxHealth());
    }

    /**
     * A way for a cape to fight another cape.
     * @param opponent The opposing cape.
     * @return         Did the opponent loose?
     */
    public boolean fight(Person opponent) {
        return this.cape.fight(opponent);
    }

    /**
     * A function to calculate damage dealt to an opponent.
     * @return damage dealt. Does not take dodge or armor into account.
     */
    @Override
    public int getDamageDealt() {
        return cape.getDamageDealt();
    }

    /**
     * A function to calculate and apply damage taken.
     * @return Is the current health <= 0?
     */
    @Override
    public boolean takeDamage(int in) {
        return cape.takeDamage(in);
    }

    /**
     * A getter for the current armor.
     * @return The amount of armor right now.
     */
    public int getArmor() {
        return cape.getArmor();
    }

    /**
     * A setter for the amount of armor.
     * @param armor The new amount of armor.
     */
    public void setArmor(int armor) {
        cape.setArmor(armor);
    }

    /**
     * A getter for the current amount of health.
     * @return The current amount of health.
     */
    @Override
    public int getCurrentHealth() {
        return cape.getCurrentHealth();
    }

    /**
     * A setter for the current amount of health.
     * WARNING: Does not allow health to go above max.
     * @param health The new amount of health.
     */
    public void setCurrentHealth(int health) {
        cape.setCurrentHealth(health);
    }

    /**
     * A getter for the current damage value.
     * @return The amount of damage.
     */
    @Override
    public int getDamage() {
        return cape.getDamage();
    }

    /**
     * A setter for the amount of damage.
     * @param damage The new damage value.
     */
    @Override
    public void setDamage(int damage) {
        cape.setDamage(damage);
    }

    /**
     * A getter for the dodge damage reduction.
     * @return The amount of dodge.
     */
    @Override
    public int getDodge() {
        return cape.getDodge();
    }

    /**
     * A setter for the amount of dodge.
     * @param dodge The new amount of dodge.
     */
    @Override
    public void setDodge(int dodge) {
        cape.setDodge(dodge);
    }

    /**
     * A getter for the max amount of health.
     * @return The maximum amount of health.
     */
    @Override
    public int getMaxHealth() {
        return cape.getMaxHealth();
    }

    /**
     * A setter for the amount of health.
     * @param health The new maximum amount of health.
     */
    @Override
    public void setMaxHealth(int health) {
        cape.setMaxHealth(health);
    }
}
