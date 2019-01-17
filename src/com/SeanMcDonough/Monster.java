package com.SeanMcDonough;

import java.util.Random;

public abstract class Monster extends Character {
    private int xp;
    public Monster(String name, int health, int defense, int atkPwr, int maxHealth, int xp) {
        super(name, health, defense, atkPwr, maxHealth);
        this.xp = xp;

    }

    public int getXp(){
        return this.xp;
    }

    public void takeTurn(Player player){
        System.out.println("take turn");
        Random rand = new Random();
        int randNum = rand.nextInt(8);

        if(randNum <= 5) {
          attack(player);
        } else if (randNum <= 7) {
            specialAction(player);
        } else if (randNum == 8 && getCurrentHealth() < (getMaxHealth()/2)) {
            System.out.println(" is retreating!");
        }

    }

//    public Player monsterAttack(Player player){
//        int damage;
//        System.out.println(super.getName() + " attacks!");
//        damage = calculateAttackDamage(super.getAttackPower(), player.getDefense());
//        dealDamage(damage, player);
//        return player;
//    }

    public void specialAction(Player player){
        System.out.println("Special action");
    }

    public void getLoot(Player player){

    }






}
