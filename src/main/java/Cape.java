import static java.lang.Math.floor;

public class Cape implements Person {
    int damage;
    int dodge;
    int armor;
    int health;
    /**
     * A basic constructor.
     */
    Cape() {
        damage = 1;
        dodge = 0;
        armor = 0;
        health = 5;
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
        health -= dam;
        return health <= 0;
    }

    /**
     * A getter for health
     */
    @Override
    public int getHealth() {
        return health;
    }
}
