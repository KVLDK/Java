/**
 V, Kara
 COP-3252
 Assignment 5
 3/21/17
 Driver to run the Java Bean Forest game and interact with user
 */

import java.lang.NumberFormatException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Random;

public class JavaBeanForestDriver {

    public static void main(String[] args) {
        //obtain user input
        Scanner input = new Scanner(System.in);
        Random randomSelection = new Random();
        Boolean play = true; //if false, user exits game

        //variables for user to input
        String myKnightName, myKnightWeapon;

        while (play == true) {
            System.out.printf("\n%s\n%s\n%s\n%s\n%s\n%s\n\n", "You have now entered the Java Bean Forest...", "It's midnight. You can see nothing through the darkness.",
                    "But strange, mysterious sounds abound.",
                    "The forest is full of evil creatures that are unwelcoming to visitors", "Through the forest is the only way for your knight to reach his destination",
                    "Be on your guard...an attack could be waiting around every corner. Let's begin.");

            //create your knight
            System.out.println("\nFirst we shall create your brave warrior.");
            myKnightName = NameSelection();

            myKnightWeapon = WeaponSelection();

            System.out.println("\nArmor to defend against attack and starting Health shall be chosen randomly.");

            Knight knight = new Knight(myKnightName, myKnightWeapon);

            JOptionPane.showMessageDialog(null, "Summary of your knight:\n" + knight); //calls toString method

            System.out.printf("\n%s\n%s\n", "Now the fun begins.", "How many encounters with the evil unknown are you willing to risk?");
            Boolean valid = false; //check if user made valid selection

            int encounters = 0;
            int enemies = 0;

            //user chooses how many enemy encounters they'd like
            do {
                try {
                    int choice = Integer.parseInt(JOptionPane.showInputDialog("Select between 2 and 4 encounters."));  //try statement ensures user selects and integer
                    switch (choice) {
                        case 2:
                            enemies = choice;
                            valid = true;
                            break;

                        case 3:
                            enemies = choice;
                            valid = true;
                            break;

                        case 4:
                            enemies = choice;
                            valid = true;
                            break;

                        default:
                            System.out.println("\nPlease choose 2, 3, or 4.");
                            valid = false;
                            break;
                    }
                }
             catch (NumberFormatException inputMismatch) {
                System.out.println("\nYou must enter an integer value. Please reenter your decision.");
            }

            } while (valid == false);

            JOptionPane.showMessageDialog(null, "      /\\" + "\n    /    \\" + "\n  /        \\     Enter the Java Bean Forest... \n"
                    + "/_____\\" + "\n      |");

            //loop that tracks the number of encounters that have occurred
            while (encounters < enemies) {
                Enemy enemy = Enemy.getRandomEnemy();
                JOptionPane.showMessageDialog(null, "An enemy creature has emerged!\n" + enemy);

                int nextMove = 1 + randomSelection.nextInt(2);
                int resetHealth = knight.getHealth();
                int round = 1;

                //loop that tracks knight & enemy health each round
                while ((knight.getHealth() > 0) && (enemy.getHealth() > 0)) {
                    int damageCreated;
                    int damageInflicted;


                    if (nextMove % 2 == 0) {
                        try {                                    //following 2 try statements ensure negative damage isn't passed to takeDamage
                            damageCreated = knight.fight();
                            damageInflicted = enemy.takeDamage(damageCreated, knight.getWeapon());
                            JOptionPane.showMessageDialog(null, "*** ROUND " + round + " ***\n\nYour knight was swift enough to make the first attack with their "+ knight.getWeapon() + "!\n\nROUND SUMMARY:\nThe " +
                            enemy.getName() + " tried taking " + damageCreated + " health points from your knight.\nThe use of their " + knight.getArmor() + " led them to lose only " + damageInflicted
                            + "\n\nStats: " + knight + enemy);
                            ++nextMove;
                            ++round;
                        } catch (InvalidDamageException invalidException) {
                            System.out.println("Invalid value. Damage cannot be negative. Restart round.");
                        }
                    } else
                        try {
                            damageCreated = enemy.fight();
                            damageInflicted = knight.takeDamage(damageCreated, enemy.getWeapon());
                            JOptionPane.showMessageDialog(null, "*** ROUND " + round + " ***\n\nThe enemy as caught you by surprise and attacked first using their "+ enemy.getWeapon() + "!\n\nROUND SUMMARY:\nYour knight " +
                                    knight.getName() + " tried taking " + damageCreated + " health points from the creature.\nThe use of their " + enemy.getArmor() + " led them to lose only " + damageInflicted
                                    + "\n\nStats: " + knight + enemy);
                            ++nextMove;
                            round++;
                        } catch (InvalidDamageException invalidException) {
                            System.out.println("Invalid value. Damage cannot be negative. Restart round.");

                        }
                }

                //determines if knight won the round or not
                if (knight.getHealth() > 0) {
                    JOptionPane.showMessageDialog(null, "Your knight has won the battle!! Here are his ending stats:" + knight);
                    knight.setHealth(resetHealth);
                    ++encounters;
                } else {
                    JOptionPane.showMessageDialog(null, "Your knight has been defeated by the " + enemy.getName() + ". Here were your ending stats:" + knight);
                    break;
                }

            }

            //determines if knight survived the Java Bean Forest
            if (encounters == enemies)
                JOptionPane.showMessageDialog(null, "Congratulations!! Your knight has survived the Java Bean Forest with bravery and poise");

            else
                JOptionPane.showMessageDialog(null, "Your knight has perished inside the Java Bean Forest. Perhaps next time they will be better prepared.");


            Boolean continueLoop = true;

            System.out.printf("\n%s\n%s\n", "Thank you for playing. Would you like to try your chances again inside the Java Bean Forest?",
                    "Surely you must want to defend your new title, or redeem your name?");
            do {
                try {                          //try statement ensures user entered an integer value

                    int playAgain = Integer.parseInt(JOptionPane.showInputDialog("Play again (1) or quit like a coward (2)"));

                    if (playAgain == 2) {
                        continueLoop = false;
                        System.out.println("\nPerhaps another time...goodbye.");
                        play = false;
                        break;}

                    if (playAgain == 1)
                        break;

                    else
                        System.out.println("\nPlease choose 1 or 2.");

                } catch (NumberFormatException inputMismatch) {
                    System.out.println("\nYou must enter an integer value. Please reenter your decision.");
                }
            } while (continueLoop);

        }
    }


    //user chooses knight's name
    public static String NameSelection() {
        String nameSelection = JOptionPane.showInputDialog("What will you name this knight?");
        return nameSelection;
    }

    //user chooses knight's weapon
    public static String WeaponSelection() {
        System.out.printf("%s\n%s\n%s\n%s\n", "You may choose one of three weapons:", "1. Hammer",
                "2. Bow & Arrow", "3. Long Sword");

        Boolean valid = false; //evaluates if user made a valid selection
        String weapon = null; //value to return

        do {
            try{                        //try statement ensures user entered an integer
            int weaponChoice = Integer.parseInt(JOptionPane.showInputDialog("Choose option 1, 2, or 3."));

            switch (weaponChoice) {
                case 1:
                    weapon = "Hammer";
                    valid = true;
                    break;
                case 2:
                    weapon = "Bow & Arrow";
                    valid = true;
                    break;
                case 3:
                    weapon = "Long Sword";
                    valid = true;
                    break;
                default:
                    System.out.println("\nPlease choose 1, 2, or 3.");
                    valid = false;
            }

            } catch (NumberFormatException inputMismatch) {
                System.out.println("\nYou must enter an integer value. Please reenter your decision.");
            }

        } while (valid == false);

        return weapon;
    }
}




