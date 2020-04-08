package main.java;

public class UndergroundCaveBuilder implements HideoutBuilder {
    private Hideout hideout;

    /**
     * A constructor for underground cave hideouts.
     * @param name       The name of the hideout.
     * @param allegiance The allegiance of the hideout. Hero or villain.
     */
    public UndergroundCaveBuilder(String name, String allegiance) {
        this.hideout = new Hideout(name, allegiance);
        buildDurability();
        buildHiddenness();
    }

    /**
     * The durability of the base.
     */
    public void buildDurability() {
        hideout.setDurability(10);
    }

    /**
     * The hiddenness of the base.
     */
    public void buildHiddenness() {
        hideout.setHiddenness(10);
    }

    /**
     * Return the built hideout.
     * @Return The built hideout.
     */
    public Hideout getHideout() {
        return this.hideout;
    }
}
