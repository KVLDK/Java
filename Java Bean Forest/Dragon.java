/**
 V, Kara
 COP-3252
 Assignment 5
 3/21/17
 
 Dragon Class
 
 */

import java.util.Random;

public class Dragon extends Enemy {

    Random randomNumber = new Random();

    public Dragon()
    {
        setName("Dragon");
        setWeapon("Fire");
        setArmor("Scales");
        setHealth(15 + randomNumber.nextInt(10));
    }

    //Dragon's version of fight method
    @Override
    public int fight()
    {
        int damage = (10 + randomNumber.nextInt(12));
        return damage;
    }

    @Override
    public String toString()
    {
        return String.format("\n*****\nCreature: %s\nWeapon: %s\nArmor: %s\nHealth: %d\n*****",
            getName(), getWeapon(), getArmor(), getHealth());
    }
}
