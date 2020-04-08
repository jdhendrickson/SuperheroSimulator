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
}
