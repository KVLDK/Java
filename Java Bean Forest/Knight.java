/**
 Vahldieck, Kara
 COP-3252
 Assignment 5
 3/21/17
 */

import java.util.Random;

public class Knight implements BattleOperations {

    private String name, weapon, armor;
    private int health;

    //array to initialize inside set functions
    String[] weaponSelection = {"Hammer", "Bow & Arrow", "Long Sword"};
    String[] armorSelection = {"Shield", "Chain Mail", "Magician's Cloak"};


    Random randomNumber = new Random();

    public Knight(String knightName, String knightWeapon) {
        name = knightName;
        weapon = knightWeapon;
        setArmor(randomNumber.nextInt(3));
        setHealth(20 + randomNumber.nextInt(10));
    }

    public void setName(String a) {
        name = a;
    }

    public String getName() {
        return name;
    }

    public void setWeapon(int b) {
        weapon = weaponSelection[b];
    }

    public String getWeapon() {
        return weapon;
    }

    public void setArmor(int c) {
        armor = armorSelection[c];
    }

    public String getArmor() {
        return armor;
    }

    public void setHealth(int d) {
        health = d;
    }

    public int getHealth() {
        return health;
    }

    public String toString() {
        return String.format("\n*****\nKnight: %s\nWeapon: %s\nArmor: %s\nHealth: %d\n*****",
                getName(), getWeapon(), getArmor(), getHealth());
    }

    //knight's implementation of fight method
    public int fight() {
        String weapon = this.getWeapon();
        int damage;

        switch (weapon) {
            case "Hammer":
                damage = (10 + randomNumber.nextInt(12));
                break;
            case "Bow & Arrow":
                damage = (10 + randomNumber.nextInt(10));
                break;
            case "Long Sword":
                damage = (10 + randomNumber.nextInt(14));
                break;
            default:
                damage = 0;
                break;
        }
        return damage;

    }


    //knight's implementation of takeDamage method
    public int takeDamage(int value, String enemyWeapon) throws InvalidDamageException {
        if (value < 0)
            throw new InvalidDamageException();

        String defense = this.getArmor();
        int damageInflicted;

        //damage taken is specialized, based off a combination of the specific enemy's weapon and the knight's particular armor, Extra Credit #1
        switch (defense) {
            case "Shield": {
                if (enemyWeapon == "Fire")
                {damageInflicted = (3 + randomNumber.nextInt(7));
                this.setHealth(this.getHealth()-damageInflicted);}

                else if (enemyWeapon == "Claws")
                {damageInflicted = (3 + randomNumber.nextInt(5));
                this.setHealth(this.getHealth()-damageInflicted);}

                else if (enemyWeapon == "Horns")
                {damageInflicted = (3 + randomNumber.nextInt(9));
                this.setHealth(this.getHealth()-damageInflicted);}

                else
                {damageInflicted = value;
                 this.setHealth(damageInflicted);}

                break;

            }

            case "Chain Mail": {
                if (enemyWeapon == "Fire")
                {damageInflicted = (3 + randomNumber.nextInt(9));
                this.setHealth(this.getHealth()-damageInflicted);}

                else if (enemyWeapon == "Claws")
                {damageInflicted = (3 + randomNumber.nextInt(7));
                this.setHealth(this.getHealth()-damageInflicted);}

                else if (enemyWeapon == "Horns")
                {damageInflicted = (3 + randomNumber.nextInt(5));
                this.setHealth(this.getHealth()-damageInflicted);}

                else
                {damageInflicted = value;
                 this.setHealth(damageInflicted);}

                break;
            }

            case "Magician's Cloak": {
                if (enemyWeapon == "Fire")
                {damageInflicted = (3 + randomNumber.nextInt(9));
                this.setHealth(this.getHealth()-damageInflicted);}

                else if (enemyWeapon == "Claws")
                { damageInflicted = (3 + randomNumber.nextInt(7));
                this.setHealth(this.getHealth()-damageInflicted);}

                else if (enemyWeapon == "Horns")
                { damageInflicted = (3 + randomNumber.nextInt(5));
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



