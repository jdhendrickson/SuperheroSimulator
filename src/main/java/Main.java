package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Main {
    static Hideout[] heros;
    static Hideout[] villain;

    /**
     * The main function.
     * @param args Command line args. First arg should be the json file being looked for.
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            initFromJson(args[1]);
        } else {
            initFromJson("SecondCityExample.json");
        }
        Person[] tempPersonArr1;
        Person[] tempPersonArr2;
        int temp1;
        int temp2;
        Boolean tempBool;
        int i = 0;//Iterator. Is used to determine the number of battles.
        while (heros[0].getTeamSize() > 0 && i < 5000) {
            //Number of battles is cut off at 5000 due to cpu speed concerns.
            i++;
            //Start of a new day
            if (heros[0].getTeamSize() == 0) {
                System.out.println("All heros have died");
                return;
            }
            System.out.println("Day " + i);
            printTeams();
            for (int j = 0; j < heros.length; j++) {
                tempPersonArr1 = heros[j].getTeam();//Get a hero team
                for (int k = 0; k < villain.length; k++) {
                    tempPersonArr2 = villain[k].getTeam();//Get a villan team
                    temp1 = getRandNumbInRange(0, tempPersonArr1.length - 1);//Pick a hero
                    System.out.print("Hero " + tempPersonArr1[temp1].getName());
                    temp2 = getRandNumbInRange(0, tempPersonArr2.length - 1);//Pick a villain
                    System.out.print(" vs Villian " + tempPersonArr2[temp2].getName());
                    System.out.print("\n");
                    //Fight!
                    tempBool = tempPersonArr1[temp1].fight(tempPersonArr2[temp2]);
                    if (tempBool) {
                        //The heros won, buff the hero who won with a new power
                        System.out.println("\tHeros won the battle");
                        tempPersonArr1[temp1] = new PowerDecorator(tempPersonArr1[temp1],
                                getRandNumbInRange(0, tempPersonArr2[temp2].getDamage()),
                                getRandNumbInRange(0, tempPersonArr2[temp2].getDodge()),
                                getRandNumbInRange(0, tempPersonArr2[temp2].getArmor()),
                                getRandNumbInRange(0, tempPersonArr2[temp2].getArmor()));
                        //Kill the loosing villan
                        if (villain[k].getTeamSize() > 0) {
                            villain[k].removePerson(tempPersonArr2[temp2]);
                        }
                        //Add a new villain
                        newVillian();
                    } else {
                        System.out.println("\tHeros lost the battle");
                        tempPersonArr2[temp2] = new PowerDecorator(tempPersonArr2[temp2],
                                getRandNumbInRange(0, tempPersonArr1[temp1].getDamage()),
                                getRandNumbInRange(0, tempPersonArr1[temp1].getDodge()),
                                getRandNumbInRange(0, tempPersonArr1[temp1].getArmor()),
                                getRandNumbInRange(0, tempPersonArr1[temp1].getArmor()));
                        if (heros[j].getTeamSize() > 0) {
                            heros[j].removePerson(tempPersonArr1[temp1]);
                        }
                    }
                }
            }
        }
    }

    /**
     * A way to add a new villain and keep the hideouts working properly.
     */
    public static void newVillian() {
        Person tempPerson = new Cape();
        boolean added = false;
        int i = 0;
        while (i < villain.length && !added) {
            if (villain[i].getTeamSize() < 5) {
                villain[i].addPerson(tempPerson);
                added = true;
            }
            i++;
        }
        //New villain added. Check to make sure that all the hideouts have people
        for (i = 0; i < villain.length; i++) {
            if (villain[i].getTeamSize() == 0) {
                //Convert the array into an ArrayList
                List<Hideout> list = new ArrayList<>();
                Collections.addAll(list, villain);
                //Remove all instances of the input Student
                list.removeAll(Arrays.asList(villain[i]));
                //Convert back into an array
                villain = list.toArray(new Hideout[list.size()]);
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
        }
        System.out.println("\tVillains:");
        for (int i = 0; i < villain.length; i++) {
            System.out.println("\t\t" + villain[i].getName());
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
        villain = new Hideout[villainHideoutArr.length()];
        for (int i = 0; i < villainHideoutArr.length(); i++) {
            hideoutObj = villainHideoutArr.getJSONObject(i);
            name = hideoutObj.getString("Name");
            if (hideoutObj.getString("Type").equals("UndergroundCave")) {
                villain[i] = new UndergroundCaveBuilder(name, "Hero").getHideout();
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
                villain[i].addPerson(new Cape(name, damage, dodge, armor, health));
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
            BufferedReader reader = new BufferedReader(new FileReader(in));
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
}
