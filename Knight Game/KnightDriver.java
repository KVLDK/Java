/**
 V, Kara
 COP-3252
 Assignment 4
 2/9/17
 
 Driver runs the Knight game
 
 */

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Random;

public class KnightDriver {

    public static void main(String[] args) {
        //obtain user input
        Scanner input = new Scanner(System.in);
        Random randomSelection = new Random();
        Boolean play = true; //if false, user exits game

        //variables for user to input
        String myKnightName, myKnightWeapon, enemyKnightName, enemyKnightWeapon;
        Knight enemyKnight = new Knight(); //dummy values so object can 1st be initialized, actual values to come later
        //knight armor and hitpoints will be assigned randomly

        while (play == true) {
            System.out.printf("\n%s\n%s\n%s\n%s\n%s\n\n", "Welcome to Camelot, land of intrigue...", "Times are dark during these Middle Ages.",
                    "As a knight, one must constantly be ready to defend ones name and title.",
                    "Prove yourself worthy to serve King Arthur...fight a knight!", "Let's begin...Knight Fight!");

            //create your knight
            myKnightName = NameSelection();

            myKnightWeapon = WeaponSelection();

            System.out.println("\nArmor to defend against attack shall be chosen randomly.");

            Knight myKnight = new Knight(myKnightName, myKnightWeapon);

            //create enemy Knight
            System.out.printf("\n%s\n%s\n\n", "Now for your opponent.", "Would you like the computer to create a knight, or would you prefer to create your own?");

            Boolean valid; //check if user made valid selection

            do {
                String opponent = JOptionPane.showInputDialog("Select computer (yes) or create yourself (no)");
                switch (opponent) {
                    case "Yes":
                    case "yes":
                        enemyKnight = CreateEnemyComputer(); //replace dummy values
                        valid = true;
                        break;

                    case "No":
                    case "no":
                        enemyKnightName = NameSelection();
                        enemyKnightWeapon = WeaponSelection();
                        enemyKnight = new Knight(enemyKnightName, enemyKnightWeapon); //replace dummy values
                        valid = true;
                        break;

                    default:
                        valid = false;
                        break;
                }
            } while (valid == false);

            System.out.printf("Summary of your knights:\n%s\n%s\n", myKnight, enemyKnight); //calls toString method

            System.out.printf("\n%s\n%s\n", "Now the fun begins.", "Would you like to fight your knights?");

            String begin = JOptionPane.showInputDialog("Fight your knights (y) or quit like a coward (n)");
            int roundCount = 1; //keeps track of rounds played
            Knight[] playerTurn = {myKnight, enemyKnight}; //will determine whose turn it is each round

            System.out.println("The starting knight will be decided randomly each round.\n");

            if (begin.equals("Y") || begin.equals("y")) {

                //if user chooses to play, enters Knight Fight game loop
                while ((myKnight.getHitpoints() > 0) && (enemyKnight.getHitpoints() > 0)) {
                    System.out.printf("\nROUND %d COMMENCE\n", roundCount);
                    Knight playing = playerTurn[randomSelection.nextInt(2)]; //assigns 1st player
                    Knight opponent; //will be second player

                    if (playing == myKnight)
                        opponent = enemyKnight;
                    else
                        opponent = myKnight;

                    playing.fight(opponent);

                    //checks points after round to determine if a winner yet, or to continue playing
                    if (opponent.getHitpoints() > 0)
                        opponent.fight(playing);
                    else {
                        playing.DeclareWinner();
                        break;
                    }

                    //checks points after round to determine if a winner yet, or to continue playing
                    if (playing.getHitpoints() > 0) {
                        System.out.printf("\n~~~~~\n\nStandings after Round %d:\n%s\n%s\n", roundCount, myKnight, enemyKnight); //calls toString method
                        ++roundCount;
                    } else {
                        opponent.DeclareWinner();
                        break;
                    }
                }
            } else {   //exit game
                System.out.println("\nPerhaps another time...goodbye.");
                play = false;
                break;
            }

            //game wrap-up
            System.out.printf("\n%s\n%s\n", "Thank you for playing. May we interest you in another round?",
                    "Surely you must want to defend your new title, or redeem your name?");

            String nextRound = JOptionPane.showInputDialog("Fight again (y) or quit like a coward (n)?");

            //if user chooses no, will exit the game
            if (nextRound.equals("N") || nextRound.equals("n")) {
                System.out.println("\nPerhaps another time...goodbye.");
                play = false;
            }
     }
 }

    public static String NameSelection()
    {
        String nameSelection = JOptionPane.showInputDialog("What will you name this knight?");
        return nameSelection;
    }

    public static String WeaponSelection() {
        System.out.printf("%s\n%s\n%s\n%s\n%s\n", "You may choose one of four weapons:", "1. Hammer",
                "2. Axe", "3. Spear", "4. Sword");

        Boolean valid; //evaluates if user made a valid selection
        String weapon; //value to return

        do {
            int weaponChoice = Integer.parseInt(JOptionPane.showInputDialog("Choose option 1, 2, 3, or 4."));

            switch (weaponChoice) {
                case 1:
                    weapon = "Hammer";
                    valid = true;
                    break;
                case 2:
                    weapon = "Axe";
                    valid = true;
                    break;
                case 3:
                    weapon = "Spear";
                    valid = true;
                    break;
                case 4:
                    weapon = "Sword";
                    valid = true;
                    break;
                default:
                    weapon = null;
                    valid = false;
            }
        } while (valid == false);

        return weapon;
    }

    //creates computer generated opponent
    public static Knight CreateEnemyComputer()
    {
        Knight enemyKnight = new Knight();
        return enemyKnight;
    }



}


