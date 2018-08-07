package com.SeanMcDonough;

import java.util.Random;
import java.util.Scanner;

public class Battle {
    private Monster monster;
    private Player player;

    public Battle(Monster monster, Player player) {
        this.monster = monster;
        this.player = player;

    }

    public boolean run() {
        boolean quit = false;
        int choice;
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        printer.printBattleMenu(monster);
        while (!quit) {
            if(!player.getIncapacitated()) {
                System.out.println("Make your selection:");
                choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                        if (retreat()) {
                            System.out.println("Retreat successful!");
                            quit = true;
                        } else {
                            System.out.println("The " + monster.getName() + " blocks your path!");
                        }
                        monster.takeTurn(this.player);
                        break;
                    case 1:
                        player.attack(monster);
                        if (checkMonsterDeath(monster.getCurrentHealth())) {
                            quit = true;
                            monster.getLoot(player);
                            break;
                        }
                        monster.takeTurn(this.player);
                        break;
                    case 2:
                        System.out.println("Casting Spells");
                        break;
                    case 3:
                        printer.printCharacterStats(player);
                        boolean back = false;
                        System.out.print("Press enter");
                        while(!back){
                            scanner.nextLine();
                            String input = scanner.nextLine();
                            if(input != null){
                                back = true;
                            }
                        }
                        break;
                    default:
                        break;
                }

            }


            if(checkPlayerDeath(player.getCurrentHealth())){
                System.out.println("Thou art dead");
                return true;
            }
        }//while
        return false;
    }//run
    private boolean checkPlayerDeath(int health){
        if(health<=0){
            return true;
        } else {
            return false;
        }
    }

    public boolean retreat(){
        Random rand = new Random();
        int randNum = rand.nextInt(5);
        if(randNum <= 3) {
            return true;
        }
        return false;
    }
//    public void playerAttack(){
//        int damage;
//        System.out.println(player.getName() +" attacks!");
//        damage = calculateAttackDamage(player.getAttackPower(), monster.getDefense());
//        dealDamage(damage, monster);
//
//    }

//    public void monsterTurn(){
//        Random rand = new Random();
//        int randNum = rand.nextInt(8);
//
//        if(randNum <= 5) {
//            monsterAttack();
//        }
//        if(randNum <= 7) {
//            monster.specialAction();
//        }
//        if(randNum == 8) {
//            System.out.println(" is retreating!");
//        }
//    }


//    public void dealDamage(int damage, Character character){
//        if(damage > 0) {
//            character.reduceHealth(damage);
//            System.out.println(damage + " damage dealt to " + character.getName());
//        }
//    }
//    public int calculateAttackDamage(int atkPwr, int defense){
//        int damage;
//        int damageBonus = (int)((atkPwr - defense) * .2);
//        damage = damageBonus + atkPwr;
//        return damage;
//
//    }
    public boolean checkMonsterDeath(int health){
        if(health<=0){
            System.out.println(monster.getName() + " was defeated!");
            player.gainXP(monster.getXp());

            return true;
        }
        return false;
    }
}








