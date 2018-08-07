package com.SeanMcDonough;

public abstract class Character {

    private int currentHealth;
    private String name;
    private int defense;
    private int attackPower;
    private int maxHealth;
    private String raceString;


    public Character(){

    }

    public Character(String name, int currentHealth, int defense, int attackPower, int maxHealth){
        this.attackPower = attackPower;
        this.defense = defense;
        this.currentHealth = currentHealth;
        this.name = name;
        this.maxHealth = maxHealth;
    }
    public void increaseAttackPower(int increase){
        this.attackPower += increase;
    }
    public void increaseDefense(int increase) {
        this.defense += increase;
    }

    public void recoverHealth(int increase){
        if((this.currentHealth += increase) > maxHealth){
            this.currentHealth = maxHealth;
        } else{
            this.currentHealth += increase;
        }
    }
    public void reduceHealth(int amount){
        if(amount>0) {
            this.currentHealth -= amount;
        }
    }
    public int calculateAttackDamage(int atkPwr, int defense){
        int damage;
        int damageBonus = (int)((atkPwr - defense) * .2);
        damage = damageBonus + atkPwr;
        return damage;

    }
    public void dealDamage(int damage, Character character){
        if(damage > 0) {
            character.reduceHealth(damage);
            System.out.println(damage + " damage dealt to " + character.getName());
        }
    }
//    public Monster attack(Monster monster){
//        int damage;
//        System.out.println(this.getName() +" attacks!");
//        damage = calculateAttackDamage(this.getAttackPower(), monster.getDefense());
//        dealDamage(damage, monster);
//        return monster;
//    }

//    public Player attack(Player player){
//        int damage;
//        System.out.println(this.getName() +" attacks!");
//        damage = calculateAttackDamage(this.getAttackPower(), player.getDefense());
//        dealDamage(damage, player);
//        return player;
//    }
public void attack(Character player){
        int damage;
        System.out.println(this.getName() +" attacks!");
        damage = calculateAttackDamage(this.getAttackPower(), player.getDefense());
        dealDamage(damage, player);
    }


    public void increaseMaxHealth(int amount){
        maxHealth += amount;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public void setMaxHealth(int amount){
        this.maxHealth = amount;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public String getRaceString() {
        return raceString;
    }


    public void setRaceString(String raceString) {
        this.raceString = raceString;
    }
}
