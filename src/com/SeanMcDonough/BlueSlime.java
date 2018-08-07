package com.SeanMcDonough;

public class BlueSlime extends Monster {
    public BlueSlime() {
        super("Blue Slime", 7, 7, 4, 7, 3);
    }

    public void specialAction(Player player){
        System.out.println("Slime waves at you!");
        dealDamage(((int)(getAttackPower()*2.5) - player.getDefense()), player);

    }
}
