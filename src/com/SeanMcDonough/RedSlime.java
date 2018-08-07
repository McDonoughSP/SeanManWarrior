package com.SeanMcDonough;

public class RedSlime extends Monster {
    public RedSlime() {
        super("RedSlime", 10, 10, 6, 10, 5);
    }

    public void specialAction(Player player){
        System.out.println("Slime waves at you!");
        dealDamage(((getAttackPower()*2) - player.getDefense()), player);

    }
}
