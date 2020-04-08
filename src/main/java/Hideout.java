public class Hideout implements HideoutPlan {
    private Person[] team;
    private int durability;
    private int hiddenness;

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
     * Sets the durabilty of the material being used.
     * @param in The durability of the material used.
     */
    public void setDurability(int in) {
        this.durability = in;
    }

    /**
     * Sets the hiddenness of the location of the base.
     * @param in The hiddenness of the base.
     */
    public void setHiddenness(int in) {
        this.hiddenness = in;
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
