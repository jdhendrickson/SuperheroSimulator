package main.java;

public abstract class CapeDecorator implements Person {
    protected Person cape;

    /**
     * A basic cape decorator.
     * @param cape The cape to be decorated.
     */
    public CapeDecorator(Person cape) {
        this.cape = cape;
    }
}
