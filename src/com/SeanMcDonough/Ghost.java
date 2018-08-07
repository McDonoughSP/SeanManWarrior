package com.SeanMcDonough;

public class Ghost extends Monster {

    public Ghost() {
        super("Ghost", 15, 11, 10, 15, 9);
    }

    @Override
    public void specialAction(Player player) {
        System.out.println("Boooooooo!");
        dealDamage(((int)(this.getAttackPower()*1.8) - player.getDefense()), player);
    }
}
