interface Person {

    /**
     * A function to calculate damage dealt to an opponent
     * @return damage dealt. Does not take dodge or armor into account.
     */
    int getDamageDealt();

    /**
     * A function to calculate and apply damage taken.
     */
    boolean takeDamage(int in);

    /**
     * A getter for the current armor.
     * @return The amount of armor right now.
     */
    public int getArmor();

    /**
     * A setter for the amount of armor.
     * @param armor The new amount of armor.
     */
    public void setArmor(int armor);

    /**
     * A getter for the current amount of health.
     * @return The current amount of health.
     */
    public int getCurrentHealth();

    /**
     * A setter for the current amount of health.
     * WARNING: Does not allow health to go above max.
     * @param health The new amount of health.
     */
    public void setCurrentHealth(int health);

    /**
     * A getter for the current damage value.
     * @return The amount of damage.
     */
    public int getDamage();

    /**
     * A setter for the amount of damage,
     * @param damage The new damage value.
     */
    public void setDamage(int damage);

    /**
     * A getter for the dodge damage reduction.
     * @return The amount of dodge.
     */
    public int getDodge();

    /**
     * A setter for the amount of dodge.
     * @param dodge The new amount of dodge.
     */
    public void setDodge(int dodge);

    /**
     * A getter for the max amount of health.
     * @return The maximum amount of health.
     */
    int setMaxHealth();

    /**
     * A setter for the amount of health.
     * @param health The new maximum amount of health.
     */
    public void setMaxHealth(int health);
}
