public abstract class CapeDecorator implements Person {
    protected Cape cape;

    /**
     * A basic cape decorator.
     * @param cape The cape to be decorated.
     */
    public CapeDecorator(Cape cape) {
        this.cape = cape;
    }
}
