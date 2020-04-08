interface HideoutPlan {

    /**
     * Add a new person.
     * @param in The new person to add.
     */
    void addPerson(Person in);

    /**
     * A method to remove a specified person from the list of people.
     * @param in The person to remove.
     * @return   Was the person removed sucessfully?
     */
    boolean removePerson(Person in);

    /**
     * Sets the durabilty of the material being used.
     * @param in The durability of the material used.
     */
    void setDurability(int in);

    /**
     * Gets the durabilty of the material being used.
     * @return The durability of the material used.
     */
    public int getDurability();

    /**
     * Sets the hiddenness of the location of the base.
     * @param in The hiddenness of the base.
     */
    void setHiddenness(int in);

    /**
     * Gets the hiddenness of the hideout.
     * @return The hiddenness of the hideout.
     */
    int getHiddenness();

    /**
     * Gets the team.
     * @return The list of team members.
     */
    Person[] getTeam();

    /**
     * Gets the team size.
     * @return The size of the team
     */
    int getTeamSize();

}
