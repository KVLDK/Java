/**
 V, Kara
 COP-3252
 Assignment 5
 3/21/17
 
 Harpy Class
 
 */

import java.util.Random;

public class Harpy extends Enemy {

    Random randomNumber = new Random();

    public Harpy()
    {
        setName("Harpy");
        setWeapon("Claws");
        setArmor("Wings");
        setHealth(15 + randomNumber.nextInt(10));
    }

    //Harpy's version of fight method
    @Override
    public int fight()
    {
        int damage = (10 + randomNumber.nextInt(11));
        return damage;
    }

    @Override
    public String toString()
    {
        return String.format("\n*****\nCreature: %s\nWeapon: %s\nArmor: %s\nHealth: %d\n*****",
            getName(), getWeapon(), getArmor(), getHealth());
    }
}
