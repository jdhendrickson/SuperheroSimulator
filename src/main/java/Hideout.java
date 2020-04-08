public class Hideout implements HideoutPlan {
    private Person[] team;

    /**
     * Add a new person.
     * @param in The new person to add.
     */
    @Override
    public void addPerson(Person in) {
        try {
            Person[] temp = new Person[team.length + 1];
            for (int i = 0; i < team.length; i++) {
                temp[i + 1] = team[i];
            }
            temp[0] = in;
            team = temp;
        } catch (NullPointerException e) {
            //List is null, need to create first entry
            team = new Person[1];
            team[0] = in;
        }
    }

    /**
     * Gets the team.
     * @return The list of team members.
     */
    public Person[] getTeam() {
        return team;
    }

    /**
     * Gets the team size.
     * @return The size of the team
     */
    public int getTeamSize() {
        return team.length;
    }
}
