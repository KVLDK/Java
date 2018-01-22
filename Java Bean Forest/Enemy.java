/**
 V, Kara
 COP-3252
 Assignment 5
 3/21/17
 
 Enemy Class
 
 */

import java.util.Random;

public abstract class Enemy implements BattleOperations{

    private String name, weapon, armor;
    private int health;

    Random randomNumber = new Random();

    //static method factory, instantiates enemy objects
    public static Enemy getRandomEnemy() {
        Enemy[] enemyArray = {new Dragon(), new Harpy(), new Minotaur()};
        Random randomEnemy = new Random();
        int enemySelection = randomEnemy.nextInt(3);

        return enemyArray[enemySelection];

    }

    public String getName() { return name; }

    public void setName(String a) {
        name = a;
    }

    public String getWeapon() { return weapon; }

    public void setWeapon(String b) {
        weapon = b;
    }

    public String getArmor() { return armor; }

    public void setArmor(String c) { armor = c; }

    public int getHealth() { return health; }

    public void setHealth(int d) { health = d; }

    //abstract method, no implementation in this class
    public abstract int fight();

    //enemy class version of takeDamage method
    public int takeDamage (int value, String knightWeapon) throws InvalidDamageException
    {
        if (value < 0)
            throw new InvalidDamageException();

        String defense = this.getArmor();
        int damageInflicted;

        //damage taken is specialized, based off a combination of the specific enemy's armor and the knight's particular weapon, Extra Credit #1
        switch (defense) {
            case "Scales": {
                if (knightWeapon == "Hammer")
                {damageInflicted = (3 + randomNumber.nextInt(6));
                 this.setHealth(this.getHealth()-damageInflicted);}

                else if (knightWeapon == "Bow & Arrow")
                {damageInflicted = (3 + randomNumber.nextInt(4));
                 this.setHealth(this.getHealth()-damageInflicted);}

                else if (knightWeapon == "Long Sword")
                {damageInflicted = (3 + randomNumber.nextInt(8));
                 this.setHealth(this.getHealth()-damageInflicted);}

                else
                {damageInflicted = value;
                    this.setHealth(damageInflicted);}

                break;
            }

            case "Wings": {
                if (knightWeapon == "Hammer")
                {damageInflicted = (3 + randomNumber.nextInt(4));
                 this.setHealth(this.getHealth()-damageInflicted);}

                else if (knightWeapon == "Bow & Arrow")
                {damageInflicted = (3 + randomNumber.nextInt(8));
                 this.setHealth(this.getHealth()-damageInflicted);}

                else if (knightWeapon == "Long Sword")
                {damageInflicted = (3 + randomNumber.nextInt(6));
                 this.setHealth(this.getHealth()-damageInflicted);}

                else
                {damageInflicted = value;
                 this.setHealth(damageInflicted);}

                break;

            }

            case "Brute Strength": {
                if (knightWeapon == "Hammer")
                {damageInflicted = (3 + randomNumber.nextInt(8));
                 this.setHealth(this.getHealth()-damageInflicted);}

                else if (knightWeapon == "Bow & Arrow")
                {damageInflicted = (3 + randomNumber.nextInt(6));
                 this.setHealth(this.getHealth()-damageInflicted);}

                else if (knightWeapon == "Long Sword")
                {damageInflicted = (3 + randomNumber.nextInt(4));
                 this.setHealth(this.getHealth()-damageInflicted);}

                else
                {damageInflicted = value;
                 this.setHealth(damageInflicted);}

                break;
            }

            default: {
                damageInflicted = value;
                this.setHealth(damageInflicted);
                break;
            }
        }

    return damageInflicted;


    }


}
