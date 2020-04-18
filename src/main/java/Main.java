package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Main {
    static int numbOfDays;
    static Hideout[] heros;
    static Hideout[] villains;

    /**
     * The main function.
     * @param args Command line args. First arg should be the json file being looked for.
     */
    public static void main(String[] args) {
        numbOfDays = 5000;//Have a default max number of days, just in case none is given.
        if (args.length > 0) {
            initFromJson(args[1]);
        } else {
            initFromJson("SecondCityExample.json");
        }
        Person hero;
        Person villain;
        Hideout hideoutHeros;
        Hideout hideoutVillains;
        int i = 0;//Iterator. Is used to determine the number of battles.
        while (areSupersAlive(0) && i < numbOfDays) {
            //Number of battles is cut off at a number given in json due to cpu speed concerns.
            i++;
            //Start of a new day
            System.out.println("Day " + i);
            printTeams();
            for (int j = 0; j < heros.length; j++) {
                hideoutHeros = heros[j];//Get a hero hideout
                for (int k = 0; k < villains.length; k++) {
                    hideoutVillains = villains[k];//Get a villain team
                    hero = pickRandomPerson(hideoutHeros);
                    System.out.print("Hero " + hero.getName());
                    villain = pickRandomPerson(hideoutVillains);
                    System.out.print(" vs Villain " + villain.getName());
                    System.out.print("\n");
                    //Fight!
                    fight(hero, hideoutHeros, villain, hideoutVillains);

                    //Check if all heros are dead
                    if (!areSupersAlive(1)) {
                        //All heros are dead
                        printAgeOf(2);//Villains won
                        return;
                    }
                    //Check if all villains are dead
                    if (!areSupersAlive(2)) {
                        //All villains are dead
                        printAgeOf(1);//Heros won
                        return;
                    }
                    //Add a new villain
                    newVillain();
                }
            }
        }
        printAgeOf(0);
    }

    /**
     * A method containing the actual fight stuff.
     *
     * @param person1  The first person in the fight.
     * @param hideout1 The hideout of the first person.
     * @param person2  The second person in the fight.
     * @param hideout2 The hideout of the first person.
     */
    public static void fight(Person person1, Hideout hideout1, Person person2, Hideout hideout2) {
        boolean tempBool = person1.fight(person2);
        if (tempBool) {
            //The heros won, buff the hero who won with a new power
            System.out.println("\t" + person1.getName() + " won the battle");
            person1 = new PowerDecorator(person1,
                    getRandNumbInRange(0, person2.getDamage()),
                    getRandNumbInRange(0, person2.getDodge()),
                    getRandNumbInRange(0, person2.getArmor()),
                    getRandNumbInRange(0, person2.getArmor()));
            //Kill the looser
            if (hideout2.getTeamSize() > 0) {
                hideout2.removePerson(person2);
            }
        } else {
            System.out.println("\t" + person2.getName() + " won the battle");
            person2 = new PowerDecorator(person2,
                    getRandNumbInRange(0, person1.getDamage()),
                    getRandNumbInRange(0, person1.getDodge()),
                    getRandNumbInRange(0, person1.getArmor()),
                    getRandNumbInRange(0, person1.getArmor()));
            //Kill the looser
            if (hideout1.getTeamSize() > 0) {
                hideout1.removePerson(person1);
            }
        }
    }

    /**
     * Pick a random person from an input team's hideout.
     *
     * @param in The input hideout.
     */
    public static Person pickRandomPerson(Hideout in) {
        Person out = new Cape();
        //Return a default cape just in case input was an empty hideout.
        if (in.getTeamSize() > 0) {
            int index = getRandNumbInRange(0, in.getTeamSize() - 1);
            out = in.getTeam()[index];
        }

        return out;
    }

    /**
     * A way to tell if there are still supers of the input type alive.
     *
     * @param in What type of super to look for.
     *           0 - Both heros AND villains.
     *           1 - Heros.
     *           2 - Villains.
     * @return Are there any supers of the given type alive?
     */
    public static boolean areSupersAlive(int in) {
        boolean out = false;
        Hideout temp[] = new Hideout[0];
        if (in == 0) {
            //Check both heros and villains
            if (areSupersAlive(1)) {
                //There are still heros
                return areSupersAlive(2);//Return result for villains
            } else {
                return false;//There are no heros
            }
        } else if (in == 1) {
            temp = heros;
        } else if (in == 2) {
            temp = villains;
        }
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].getTeamSize() > 0) {
                out = true;
            }
        }
        return out;
    }

    /**
     * A way to add a new villain and keep the hideouts working properly.
     */
    public static void newVillain() {
        Person tempPerson = new Cape();
        boolean added = false;
        int i = 0;
        while (i < villains.length && !added) {
            if (villains[i].getTeamSize() < 5) {
                villains[i].addPerson(tempPerson);
                added = true;
            }
            i++;
        }
        //New villain added. Check to make sure that all the hideouts have people
        for (i = 0; i < villains.length; i++) {
            if (villains[i].getTeamSize() == 0) {
                //Convert the array into an ArrayList
                List<Hideout> list = new ArrayList<>();
                Collections.addAll(list, villains);
                //Remove all instances of the input Student
                list.removeAll(Arrays.asList(villains[i]));
                //Convert back into an array
                villains = list.toArray(new Hideout[list.size()]);
                //Go back an iterator, just removed an item
                i--;
            }
        }
    }

    /**
     * A method to print out the teams in each city.
     */
    public static void printTeams() {
        System.out.println("Starting City:");
        System.out.println("\tHeros:");
        for (int i = 0; i < heros.length; i++) {
            System.out.println("\t\t" + heros[i].getName());
            for (int j = 0; j < heros[i].getTeamSize(); j++) {
                System.out.println("\t\t\t" + (heros[i].getTeam()[j]).getName());
            }
        }
        System.out.println("\tVillains:");
        for (int i = 0; i < villains.length; i++) {
            System.out.println("\t\t" + villains[i].getName());
            for (int j = 0; j < villains[i].getTeamSize(); j++) {
                System.out.println("\t\t\t" + (villains[i].getTeam()[j]).getName());
            }
        }
    }

    /**
     * A method to get a random number in a range.
     * @param min The lower bound of the number.
     * @param max The upper bound of the number.
     * @return    The quasi-random number.
     */
    static int getRandNumbInRange(int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        } else if (min == max) {
            return 0;
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Init the heros and cities from a Json file.
     * @param file The json file to parse.
     */
    public static void initFromJson(String file) {
        String jsonString = readFile(file);
        String name;
        JSONObject jsonObj = new JSONObject(jsonString);
        JSONObject hideoutObj;
        JSONObject capeObj;
        JSONArray heroHideoutArr;
        JSONArray capeArr;
        JSONArray mainArr = jsonObj.getJSONArray("City");
        int damage;
        int dodge;
        int armor;
        int health;
        //Deal with the heros
        //Get the hero hideout arr
        heroHideoutArr = mainArr.getJSONObject(0).getJSONArray("Hero Hideouts");
        heros = new Hideout[heroHideoutArr.length()];
        for (int i = 0; i < heroHideoutArr.length(); i++) {
            hideoutObj = heroHideoutArr.getJSONObject(i);
            name = hideoutObj.getString("Name");
            if (hideoutObj.getString("Type").equals("UndergroundCave")) {
                heros[i] = new UndergroundCaveBuilder(name, "Hero").getHideout();
            }
            capeArr = hideoutObj.getJSONArray("Capes");
            //Populate the hideout
            for (int j = 0; j < capeArr.length(); j++) {
                capeObj = capeArr.getJSONObject(j);
                name = capeObj.optString("Name", "Default Name");
                damage = capeObj.optInt("Damage", 1);
                dodge = capeObj.optInt("Dodge", 0);
                armor = capeObj.optInt("Armor", 0);
                health = capeObj.optInt("Health", 5);
                heros[i].addPerson(new Cape(name, damage, dodge, armor, health));
            }
        }

        //Deal with the Villains
        //Get the villain hideout arr
        JSONArray villainHideoutArr = mainArr.getJSONObject(1).getJSONArray("Villain Hideouts");
        villains = new Hideout[villainHideoutArr.length()];
        for (int i = 0; i < villainHideoutArr.length(); i++) {
            hideoutObj = villainHideoutArr.getJSONObject(i);
            name = hideoutObj.getString("Name");
            if (hideoutObj.getString("Type").equals("UndergroundCave")) {
                villains[i] = new UndergroundCaveBuilder(name, "Hero").getHideout();
            }
            capeArr = hideoutObj.getJSONArray("Capes");
            //Populate the hideout
            for (int j = 0; j < capeArr.length(); j++) {
                capeObj = capeArr.getJSONObject(j);
                name = capeObj.optString("Name", "Default Name");
                damage = capeObj.optInt("Damage", 1);
                dodge = capeObj.optInt("Dodge", 0);
                armor = capeObj.optInt("Armor", 0);
                health = capeObj.optInt("Health", 5);
                villains[i].addPerson(new Cape(name, damage, dodge, armor, health));
            }
        }
    }

    /**
     * A function to read a file.
     * @param in The name of the file.
     * @return   The contents of the file.
     */
    public static String readFile(String in) {
        System.out.println("The file being read is:" + in);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(in, StandardCharsets.UTF_8));
            String out = "";
            String read;
            while ((read = reader.readLine()) != null) {
                out += read;
            }
            reader.close();
            return out;
        } catch (IOException e) {
            System.out.println("There was an exception while reading " + in);
        }
        return "";
    }

    /**
     * Prints the end of simulation message.
     *
     * @param in What message to be printed.
     *           0 - Tie.
     *           1 - Hero.
     *           2 - Villain.
     */
    public static void printAgeOf(int in) {
        System.out.println("--------------");
        if (in == 0) {
            System.out.println("Simulation stopped due to reaching " + numbOfDays + " days.");
            System.out.println("The heros and villains are evenly matched.");
        } else if (in == 1) {
            System.out.println("All the villains have died");
            System.out.println("The heros have triumphed.");
            System.out.println("An age of light has begun.");
        } else if (in == 2) {
            System.out.println("All the heros have died");
            System.out.println("The villains have triumphed.");
            System.out.println("An age of darkness has begun.");
        }
        System.out.println("Game Over");
    }
}
