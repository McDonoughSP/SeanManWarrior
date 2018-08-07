package com.SeanMcDonough;

public class Human implements Race {
    private int raceNum;
    private int bonusStartingHealth;
    private String race;
    private int defenseBuff;
    private int attackBuff;
    public Human() {
        this.race = "Human";
        this.raceNum = 1;
        this.bonusStartingHealth = 4;
        this.defenseBuff = 0;
        this.attackBuff = 0;
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
