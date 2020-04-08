public class UndergroundCaveBuilder implements HideoutBuilder {
    private Hideout hideout;

    public UndergroundCaveBuilder(String name, String allegiance) {
        this.hideout = new Hideout(name, allegiance);
        buildDurability();
        buildHiddenness();
    }
    /**
     * How durable is the base?
     */
    public void buildDurability() {
        hideout.setDurability(10);
    }

    /**
     * How well hidden is the base?
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
