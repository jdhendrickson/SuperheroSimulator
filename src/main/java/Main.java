package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    static Hideout[] heros;
    static Hideout[] villain;

    /**
     * The main function.
     * @param args Command line args. Not used.
     */
    public static void main(String[] args) {
        heros = new Hideout[1];
        villain = new Hideout[1];
        Person hansel = new Cape();//Starting hero
        heros[0] = new UndergroundCaveBuilder("Hansel's Home", "Hero").getHideout();
        heros[0].addPerson(hansel);

        Person babaYaga = new Cape();//Starting villan
        villain[0] = new UndergroundCaveBuilder("House On Legs - Buried Edition",
                "Villain").getHideout();
        villain[0].addPerson(babaYaga);

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
                    temp2 = getRandNumbInRange(0, tempPersonArr2.length - 1);//Pick a villain
                    //Fight!
                    tempBool = tempPersonArr1[temp1].fight(tempPersonArr2[temp2]);
                    if (tempBool) {
                        //The heros won, buff the hero who won with a new power
                        System.out.println("Heros won a battle");
                        tempPersonArr1[temp1] = new PowerDecorator(tempPersonArr1[temp1],
                                getRandNumbInRange(0, tempPersonArr2[temp2].getDamage()),
                                getRandNumbInRange(0, tempPersonArr2[temp2].getDodge()),
                                getRandNumbInRange(0, tempPersonArr2[temp2].getArmor()),
                                getRandNumbInRange(0, tempPersonArr2[temp2].getArmor()));
                        //Kill the loosing villan
                        villain[k].removePerson(tempPersonArr2[temp2]);
                        //Add a new villain
                        newVillian();
                    } else {
                        System.out.println("Heros lost a battle");
                        tempPersonArr2[temp2] = new PowerDecorator(tempPersonArr2[temp2],
                                getRandNumbInRange(0, tempPersonArr1[temp1].getDamage()),
                                getRandNumbInRange(0, tempPersonArr1[temp1].getDodge()),
                                getRandNumbInRange(0, tempPersonArr1[temp1].getArmor()),
                                getRandNumbInRange(0, tempPersonArr1[temp1].getArmor()));
                        heros[k].removePerson(tempPersonArr1[temp1]);
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
}
