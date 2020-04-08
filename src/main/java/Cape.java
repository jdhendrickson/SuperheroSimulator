import static java.lang.Math.floor;

public class Cape implements Person {
    private int damage;
    private int dodge;
    private int armor;
    private int currentHealth;
    private int maxHealth;
    /**
     * A basic constructor.
     */
    Cape() {
        damage = 1;
        dodge = 0;
        armor = 0;
        maxHealth = 5;
        currentHealth = maxHealth;
    }

    /**
     * A function to calculate damage dealt to an opponent
     * @return damage dealt. Does not take dodge or armor into account.
     */
    @Override
    public int getDamageDealt() {
        return damage;
    }

    /**
     * A function to calculate and apply damage taken.
     * @return Is the current health <= 0?
     */
    @Override
    public boolean takeDamage(int in) {
        int dam = in;
        dam -= floor(dodge / 2);
        dam -= floor(armor / 2);
        currentHealth -= dam;
        return currentHealth <= 0;
    }

    /**
     * A getter for the current armor.
     * @return The amount of armor right now.
     */
    public int getArmor() {
        return armor;
    }

    /**
     * A setter for the amount of armor.
     * @param armor The new amount of armor.
     */
    public void setArmor(int armor) {
        this.armor = armor;
    }

    /**
     * A getter for the current amount of health.
     * @return The current amount of health.
     */
    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * A setter for the current amount of health.
     * WARNING: Does not allow health to go above max.
     * @param health The new amount of health.
     */
    public void setCurrentHealth(int health) {
        if(health > maxHealth) {
            this.currentHealth = maxHealth;
        } else {
            this.currentHealth = health;
        }
    }

    /**
     * A getter for the current damage value.
     * @return The amount of damage.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * A setter for the amount of damage,
     * @param damage The new damage value.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * A getter for the dodge damage reduction.
     * @return The amount of dodge.
     */
    public int getDodge() {
        return dodge;
    }

    /**
     * A setter for the amount of dodge.
     * @param dodge The new amount of dodge.
     */
    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    /**
     * A getter for the max amount of health.
     * @return The maximum amount of health.
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * A setter for the amount of health.
     * @param health The new maximum amount of health.
     */
    public void setMaxHealth(int health) {
        this.maxHealth = health;
    }
}
