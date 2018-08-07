package com.SeanMcDonough;

public class Elf implements Race {
    private int startingHealth;
    int raceNum;
    private String race;
    private int defenseBuff;
    private int attackBuff;
    private int bonusStartingHealth;



    public Elf() {
        this.race = "Elf";
        this.raceNum = 2;
        this.startingHealth = 10;
        this.defenseBuff = 2;
        this.attackBuff = 2;
        this.bonusStartingHealth = 0;
    }

    @Override
    public int getBonusStartingHealth() {
        return bonusStartingHealth;
    }

    @Override
    public String getRaceName() {
        return race;
    }

    @Override
    public int getRaceNum() {
        return raceNum;
    }

    public int getDefenseBuff(){
        return defenseBuff;
    }

    public int getAttackBuff(){
        return attackBuff;
    }
}
