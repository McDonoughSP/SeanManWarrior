package com.SeanMcDonough;

public class Slime extends Monster {

    public Slime() {
        super("Slime", 5, 5, 2, 5, 1); // default values for slime stats
    }

    public void specialAction(Player player){
        System.out.println("Slime waves at you!");
        dealDamage(((getAttackPower()*5) - player.getDefense()), player);

    }
}
