public class UndergroundCaveBuilder implements HideoutBuilder {
    private Hideout hideout;

    public UndergroundCaveBuilder() {
        this.hideout = new Hideout();
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
}
