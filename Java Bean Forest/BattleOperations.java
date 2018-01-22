/**
 V, Kara
 COP-3252
 Assignment 5
 3/21/17
 
 Common methods to be implemented in both Knight and Enemy abstract/subclasses
 
 */

public interface BattleOperations {

    int takeDamage(int value, String oppWeapon) throws InvalidDamageException;
    int fight();
}
