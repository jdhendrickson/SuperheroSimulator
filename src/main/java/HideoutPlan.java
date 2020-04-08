interface HideoutPlan {

    /**
     * Add a new person.
     * @param in The new person to add.
     */
    void addPerson(Person in);

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
