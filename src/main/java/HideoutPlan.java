interface HideoutPlan {

    /**
     * Add a new person.
     * @param in The new person to add.
     */
    void addPerson(Person in);

    /**
     * Sets the durabilty of the material being used.
     * @param in The durability of the material used.
     */
    void setDurability(int in);

    /**
     * Sets the hiddenness of the location of the base.
     * @param in The hiddenness of the base.
     */
    void setHiddenness(int in);

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
