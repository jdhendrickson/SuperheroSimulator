package main.java;

interface HideoutBuilder {

    /**
     * How durable is the base?
     */
    void buildDurability();

    /**
     * How well hidden is the base?
     */
    void buildHiddenness();

    /**
     * Return the built hideout.
     * @Return The built hideout.
     */
    Hideout getHideout();
}
