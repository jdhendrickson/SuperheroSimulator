package main.java;

import static java.lang.Math.floor;

public class Cape implements Person {
    private int damage;
    private int dodge;
    private int armor;
    private int currentHealth;
    private int maxHealth;
    private String name;

    /**
     * A basic constructor.
     */
    public Cape() {
        this.damage = 1;
        this.dodge = 0;
        this.armor = 0;
        this.maxHealth = 5;
        this.currentHealth = maxHealth;
        name = "Default name";
    }

    /**
     * A constructor that includes the cape's name.
     * @param name The name of the cape.
     */
    public Cape(String name) {
        this();
        this.name = name;
    }

    /**
     * A constructor that includes everything about a cape.
     * @param name      The name of the cape.
     * @param damage    How much damage the cape can deal.
     * @param dodge     How much damage the cape can dodge.
     * @param armor     How much armor the cape starts with.
     * @param maxHealth How much health the cape starts with.
     */
    public Cape(String name, int damage, int dodge, int armor, int maxHealth) {
        this(name);
        this.damage = damage;
        this.dodge = dodge;
        this.armor = armor;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    /**
     * A way for a cape to fight another cape.
     * @param opponent The opposing cape.
     * @return         Did the opponent loose?
     */
    public boolean fight(Person opponent) {
        int i = 0;//Iterator to make sure there will be an exit condition
        while (this.currentHealth > 0 && opponent.getCurrentHealth() > 0 && i < 25) {
            opponent.takeDamage(this.getDamageDealt());
            takeDamage(opponent.getDamageDealt());
        }
        return currentHealth >= opponent.getCurrentHealth();
    }

    /**
     * A function to calculate damage dealt to an opponent.
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
        dam -= floor(dodge);
        dam -= floor(armor);
        if (dam > 0) {
            currentHealth -= dam;
        }
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
        if (health > maxHealth) {
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
     * A setter for the amount of damage.
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

    /**
     * A getter for the person's name.
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }
}
