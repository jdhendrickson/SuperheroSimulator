public class PowerDecorator extends CapeDecorator{
    /**
     * A cape decorator thing.
     * @param impDam    The new damage value.
     * @param impDodge  The new dodge value.
     * @param impArm    The new armor value.
     * @param impHealth The new health value.
     */
    public PowerDecorator(Cape cape, int impDam, int impDodge, int impArm, int impHealth) {
        super(cape);
        cape.setDamage(impDam);
        cape.setDodge(impDodge);
        cape.setArmor(impArm);
        cape.setMaxHealth(impHealth);
        cape.setCurrentHealth(impHealth);
    }

    @Override
    public int getDamageDealt() {
        return 0;
    }

    @Override
    public boolean takeDamage(int in) {
        return false;
    }

    @Override
    public int getCurrentHealth() {
        return 0;
    }
}
