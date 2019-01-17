package com.SeanMcDonough;

import java.util.Random;
import java.util.Scanner;

public class Battle {
    private Monster monster;
    private Player player;
    private int startX;
    private int startY;

    public Battle(Monster monster, Player player, int startX, int startY) {
        this.monster = monster;
        this.player = player;

    }

    public void run() {
        boolean quit = false;
        boolean playerTurn = true;
        int choice;
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        printer.printBattleMenu(monster);

        while (!quit) {
            boolean stats = false;
            boolean spells = false;
            if(playerTurn) {
                System.out.println("Make your selection:");
                choice = scanner.nextInt();
                switch (choice) {
                    case 0://RETREAT
                        if (retreat()) {
                            System.out.println("Retreat successful!");
                            quit = true;
                        } else {
                            System.out.println("The " + monster.getName() + " blocks your path!");
                        }
                        break;

                    case 1://ATTACK
                        player.attack(monster);
                        if (checkMonsterDeath(monster.getCurrentHealth())) {
                            quit = true;
                            monster.getLoot(player);
                            break;
                        }
                        break;
                    case 2://SPELLS
                        System.out.println("Casting Spells");
                        spells = true;
                        break;
                    case 3://STATS
                        printer.printCharacterStats(player);
                        boolean back = false;
                        stats = true;
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
                }//switch
                if(!stats && !spells) {
                    playerTurn = false;
                }
            }//if(playerTurn)
            if(!quit && !stats && !spells) {
                monster.takeTurn(this.player);
                playerTurn = true;
            }


            if(checkPlayerDeath(player.getCurrentHealth())){
                System.out.println("Thou art dead");
                System.out.println("Returning to start of map.");
                player.setXCoord(startX);
                player.setYCoord(startY);
                player.setCurrentHealth(1);
                quit = true;

            }
        }//while

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
        if(randNum <= 4) {
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








