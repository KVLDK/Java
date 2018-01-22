/**
 V, Kara
 COP-3252
 Assignment 5
 3/21/17
 
 Minotaur Class
 
 */

import java.util.Random;

public class Minotaur extends Enemy {

    Random randomNumber = new Random();

    public Minotaur()
    {
        setName("Minotaur");
        setWeapon("Horns");
        setArmor("Brute Strength");
        setHealth(15 + randomNumber.nextInt(10));
    }

    //Minotaur's version of fight method
    @Override
    public int fight()
    {
        int damage = (10 + randomNumber.nextInt(10));
        return damage;
    }

    @Override
    public String toString()
    {
        return String.format("\n*****\nCreature: %s\nWeapon: %s\nArmor: %s\nHealth: %d\n*****",
            getName(), getWeapon(), getArmor(), getHealth());
    }
}
