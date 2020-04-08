public class Hideout implements HideoutPlan {
    private Person[] team;
    private int durability;
    private int hiddenness;

    /**
     * Basic constructor
     */
    public Hideout() {
        durability = 0;
        hiddenness = 0;
    }

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
     * The team from this hideout fights with the team from an opposing hideout.
     * @param opponent The opposing hideout.
     * @return         Did the opponent loose?
     */
    boolean fight(Hideout opponent) {
        int totalHealth = getTotalHealth();
        int totalDef = getTotalDefense();
        int totalDam = getTotalDamage();
        int oppHealth = opponent.getTotalHealth();
        int oppDef = opponent.getTotalDefense();
        int oppDam = opponent.getTotalDamage();

        int tempDam;
        int i = 0;//Iterator to make sure there will be an exit condition
        while (totalHealth > 0 && oppHealth > 0 && i < 25) {
            //There is still a fight going on
            tempDam = totalDam - (oppDef / 2);
            if (tempDam > 0) {
                oppHealth -= tempDam;
            }
            tempDam = oppDam - (totalDef / 2);
            if (tempDam > 0) {
                totalHealth -= tempDam;
            }
            i++;
        }

        return totalHealth >= oppHealth;
    }

    /**
     * Sets the durabilty of the material being used.
     * @param in The durability of the material used.
     */
    public void setDurability(int in) {
        this.durability = in;
    }

    /**
     * Gets the durabilty of the material being used.
     * @return The durability of the material used.
     */
    public int getDurability() {
        return this.durability;
    }

    /**
     * Sets the hiddenness of the location of the base.
     * @param in The hiddenness of the base.
     */
    public void setHiddenness(int in) {
        this.hiddenness = in;
    }

    /**
     * Gets the hiddenness of the hideout.
     * @return The hiddenness of the hideout.
     */
    public int getHiddenness() {
        return this.hiddenness;
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

    /**
     * Gets the total damage of the entire team.
     * @return The total health of the team.
     */
    int getTotalDamage() {
        int temp = 0;
        for (int i = 0; i <  getTeamSize(); i++) {
            temp += this.team[i].getDamageDealt();
        }
        return temp;
    }

    /**
     * Gets the total defense of the entire team.
     * @return The total defense of the team.
     */
    int getTotalDefense() {
        int temp = 0;
        for (int i = 0; i <  getTeamSize(); i++) {
            temp += this.team[i].getArmor() + this.team[i].getDodge();
        }
        return temp;
    }

    /**
     * Gets the total current health of the entire team.
     * @return The total health of the team.
     */
    int getTotalHealth() {
        int temp = 0;
        for (int i = 0; i <  getTeamSize(); i++) {
            temp += this.team[i].getCurrentHealth();
        }
        return temp;
    }
}
