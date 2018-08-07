package com.SeanMcDonough;

public class Printer {
    public Printer() {
    }

    public void printTravelMenu(){
        System.out.println(
                "8 - Move North " +
                "6 - Move East " +
                "2 - Move South " +
                "4 - Move West " +
                "5 - Show Coords " +
                "7 - Print Map " +
                "0 - Main Menu");

    }
    public void printMainMenu(){
        System.out.println("Main Menu:\n" +
                "Q - Quit\n" +
                "T - Travel\n" +
                "R - Rest\n" +
                "S - Spells\n" +
                "C - Character Stats\n" +
                "V - Save Game\n" +
                "P - Print Main Menu");
    }
    public void printIntro(){
        System.out.println("The King requests your aid in slaying the Dragon.\n" +
                "You accept and begin your quest, heading into the forest.");
    }
    public void printCharacterStats(Player player){

        System.out.println("Name: " + player.getName());
        System.out.println("Current Health: " + player.getCurrentHealth());
        System.out.println("Race: " + player.getRaceString());
        System.out.println("Level: " + player.getLevel());
        System.out.println("XP: " + player.getXp());
        System.out.println("NextLvl: " + player.getNextLvlXp());
        System.out.println("Attack: " + player.getAttackPower());
        System.out.println("Defense: " + player.getDefense());
    }
    public void printBattleMenu(Character monster){
        System.out.println("A wild " + monster.getName() + " appears!");
        System.out.println("Battle Menu\n" +
                "0 - Retreat " +
                "1 - Attack " +
                "2 - Cast Spell " +
                "3 - Print Character Stats" +
                "4 - Options");

    }
    public void printMap(Map map, int x, int y){
        map.printMap(x, y);
    }

}
