/**
 Vahldieck, Kara
 COP-3252
 Assignment 4
 2/9/17
 */

import java.util.Random;

public class Knight {

    private String name, weapon, armor;
    private int hitpoints;

    String[] randomNames = {"Sir Lancelot", "Sir Gallahad", "Sir Robin"};
    String[] weaponSelection = {"Hammer", "Axe", "Spear", "Sword"};
    String[] armorSelection = {"Leather", "Shield", "Chain Mail", "Magician's Cloak"};


    Random randomNumber = new Random();

    public Knight(String knightName, String knightWeapon)
    {
        name = knightName;
        weapon = knightWeapon;
        setArmor(randomNumber.nextInt(3));
        setHitpoints(50 + randomNumber.nextInt(10));

    }

    public Knight()
    {
        setRandName(randomNumber.nextInt(3));
        setWeapon(randomNumber.nextInt(3));
        setArmor(randomNumber.nextInt(3));
        setHitpoints(50 + randomNumber.nextInt(10));
    }

    public void setRandName(int a)
    {
        name = randomNames[a];
    }

    public void setName(String a)
    {
        name = a;
    }

    public String getName()
    {
        return name;
    }

    public void setWeapon(int b)
    {
        weapon = weaponSelection[b];
    }

    public String getWeapon()
    {
        return weapon;
    }

    public void setArmor(int c)
    {
        armor = armorSelection[c];
    }

    public String getArmor()
    {
        return armor;
    }

    public void setHitpoints(int d)
    {
        hitpoints = d;
    }

    public int getHitpoints()
    {
        return hitpoints;
    }

    public String toString()
    {
        return String.format("\n*****\nKnight: %s\nWeapon: %s\nArmor: %s\nHitpoints: %d\n*****",
                getName(), getWeapon(), getArmor(), getHitpoints());
    }

    public void fight(Knight k2)
    {
        int damage = this.WeaponDamage();
        int protection = k2.ArmorProtection();
        int damageTotal = damage - protection;
        int health = k2.getHitpoints() - damageTotal;

        k2.setHitpoints(health);
        System.out.printf("\n%s hath struck a blow of %d points.\nTheir opponents armor spared them %d, for a total of %d hitpoints deducted!\n",
                this.getName(), damage, protection, damageTotal);
    }

    //determine how many points taken in an attack
    public int WeaponDamage()
    {
        String weapon = this.getWeapon();
        int damage;

        switch (weapon) {
            case "Hammer":
                damage = (10 + randomNumber.nextInt(10));
                break;
            case "Axe":
                damage = (10 + randomNumber.nextInt(12));
                break;
            case "Spear":
                damage = (10 + randomNumber.nextInt(14));
                break;
            case "Sword":
                damage = (10 + randomNumber.nextInt(16));
                break;
            default:
                damage = 0;
                break;
        }

       return damage;
    }

    //determines how many points saved after an attack
    public int ArmorProtection()
    {
        String armor = this.getArmor();
        int protection;

        switch(armor) {
            case "Leather":
                protection = (1 + randomNumber.nextInt(3));
                break;
            case "Shield":
                protection = (1 + randomNumber.nextInt(5));
                break;
            case "Chain Mail":
                protection = (1 + randomNumber.nextInt(7));
                break;
            case "Magician's Cloak":
                protection = (1 + randomNumber.nextInt(9));
                break;
            default:
                protection = 0;
                break;
        }
         return protection;
    }

    //prints winner
    public void DeclareWinner()
    {
        System.out.printf("\n~~~~~\n\nThe winner of the battle is %s!!\nTheir final stats:\n%s\n", this.getName(), this);

    }

}
