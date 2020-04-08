package main.java;

interface HideoutBuilder {

    /**
     * The durability of the base.
     */
    void buildDurability();

    /**
     * The hiddenness of the base.
     */
    void buildHiddenness();

    /**
     * Return the built hideout.
     * @Return The built hideout.
     */
    Hideout getHideout();
}
