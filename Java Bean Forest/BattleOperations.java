/**
 Vahldieck, Kara
 COP-3252
 Assignment 5
 3/21/17
 */

public interface BattleOperations {

    //common methods to be implemented in both Knight and Enemy abstract/subclasses
    int takeDamage(int value, String oppWeapon) throws InvalidDamageException;
    int fight();
}
