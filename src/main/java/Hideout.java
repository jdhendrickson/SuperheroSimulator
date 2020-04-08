import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hideout implements HideoutPlan {
    private String name;
    private String allegiance;
    private Person[] team;
    private int durability;
    private int hiddenness;

    /**
     * Basic constructor
     */
    public Hideout(String name, String allegiance) {
        this.name = name;
        this.allegiance = allegiance;
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
     * A method to remove a specified person from the list of people.
     * @param in The person to remove.
     * @return   Was the person removed sucessfully?
     */
    public boolean removePerson(Person in) {
        //Convert the array into an ArrayList
        List<Person> list = new ArrayList<>();
        Collections.addAll(list, team);
        //Remove all instances of the input Student
        list.removeAll(Arrays.asList(in));
        //Convert back into an array
        team = list.toArray(new Person[list.size()]);
        return true;
    }

    /**
     * Gets the allegiance.
     * Should be "Hero" or "Villan"
     * @return The allegiance of the team.
     */
    public String getAllegiance() {
        return allegiance;
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
     * Gets the name of the hideout.
     * @return The name of the hideout.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the team.
     * @return The list of team members.
     */
    public Person[] getTeam() {
        return team.clone();
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
