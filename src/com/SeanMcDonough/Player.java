package com.SeanMcDonough;

import java.util.List;

public class Player extends Character {
    private Race race;
    private List<String> bossKeyList;

    private int raceNum;
    private int xp;
    private int nextLvlXp;
    private int level;
    private int baseHealth;
    private int baseAttackPower;
    private int baseDefense;
    private int xCoord;
    private int yCoord;
    private boolean incapacitated;


    public Player(){
    //used only for loading the saved character. all stats will be passed from the database.
    }

    public Player(String name, int choice){
        super(name, 10, 5, 5, 10);
        this.race = chooseRace(choice);
        this.level = 1;
        this.xp = 0;
        this.nextLvlXp = 10;
        this.baseHealth = 10;
        this.baseDefense = 5;
        this.baseAttackPower = 5;

        applyRaceAtr();

    }
    //used while creating a new character. the previous menu requests user input to choose a number
    //corresponding to a race. that integer is passed to the player constructor then to this method.
    //raceNum and raceString variables are set in this method(only for new players) loaded players
    //use the setRace(String) method.
    public Race chooseRace(int race){
        Race newRace;
        switch(race){

            case 1:
                 newRace = new Human();
                System.out.println("You chose Human.");
                this.raceNum = 1;
                super.setRaceString("human");
                 return newRace;
            case 2:
                 newRace = new Elf();
                System.out.println("You chose Elf.");
                this.raceNum = 2;
                super.setRaceString("elf");
                return newRace;
        }
        return null;
    }


//
    //used while constructing the player, applies the race attributes on top
    // of the predefined base player stats.
    //Using one method to apply race attributes. Each race has all the bonus fields but
    //most will be 0. The method will try to add all the possible bonus fields.
    //Only the attributes special to each race will have a value to be added.
    private void applyRaceAtr(){
        super.setCurrentHealth(race.getBonusStartingHealth() + getCurrentHealth());//health bonus
        super.setMaxHealth(race.getBonusStartingHealth() + this.getMaxHealth());//health bonus
        this.raceNum = race.getRaceNum();//race number
        setDefense((getDefense() + race.getDefenseBuff()));//adding defense buff
        setAttackPower(race.getAttackBuff() + getAttackPower());//adding attack buff
    }

    //takes the xp amount stored in the monster and passes it to the player's xp.
    //then checks to see if the total xp has reached the next level.
    public void gainXP(int amount){
       xp += amount;
       checkLvlUp();
    }
    //checks to see if player xp has reached next level xp.
    //doubles next level xp.
    public void checkLvlUp(){
        if(xp >= nextLvlXp){
            levelUp();
            this.nextLvlXp = (nextLvlXp*2) + (nextLvlXp / 3);
        }
    }

    //once checkLevelUp() has determined the player has leveled up, this method
    //increments the player level variable and calls levelUpHealth, levelUpAtkPwr,
    //and levelUpDefense which increase player baseHealth/max health, baseAttackPower, and defense/base defense.
    public void levelUp(){
        System.out.println("Congratulations, you've leveled up!");
        this.level++;
        levelUpHealth();
        levelUpAtkPwr();
        levelUpDefense();
    }

    //increases baseHealth by 10%. Fully recovers health. Only used in the levelUp() method.
    private void levelUpHealth(){
        int healthIncrease = (int)(getBaseHealth() * .1);
        baseHealth += healthIncrease;
        super.increaseMaxHealth(healthIncrease);
        super.recoverHealth(super.getMaxHealth());
    }
    //increases baseAttackPower by 10%. lower levels will be less than 1 so the minimum
    //increase is 1. Only used in the player.levelUp() method
    private void levelUpAtkPwr(){
        int attackPowerIncrease = (int)(baseAttackPower * .1);
        if(attackPowerIncrease<1){
            attackPowerIncrease = 1;
        }
        baseAttackPower += attackPowerIncrease;
        super.increaseAttackPower(attackPowerIncrease);
    }
    //increases baseDefense by 10%. lower levels will be less than 1 so the minimum
    //increase is 1. Only used in the player.levelUp() method
    private void levelUpDefense(){
        int defenseIncrease = (int)(baseDefense * .1);
        if(defenseIncrease<1){
            defenseIncrease = 1;
        }
        baseDefense += defenseIncrease;
        super.increaseDefense(defenseIncrease);
    }

    //Used for loading character, pass the race as a string and determines which race object to create
    //Sets the raceString variable in the Character class.
    public void setRace(String raceString) {
        if(raceString.equals("human")) {
            Human human = new Human();
            this.race = human;
            super.setRaceString(raceString);
        }
    }

    public void saveBossKey(String key){
        bossKeyList.add(key);
    }

    public void setBossKeyList(List<String> bossKeyList) {
        this.bossKeyList = bossKeyList;
    }

    public boolean findBossKey(String key){
        for(String string: bossKeyList){
            if(string.equals(key)){
                return true;
            }
        }
        return false;

    }

    public List<String> getBossKeyList() {
        return bossKeyList;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public boolean isIncapacitated() {
        return incapacitated;
    }

    public int getRaceNum() {
        return raceNum;
    }

    public int getLevel(){
        return this.level;
    }

    public int getXp(){
        return this.xp;
    }

    public int getBaseHealth(){
        return baseHealth;
    }

    public Race getRace() {
        return race;
    }

    public int getNextLvlXp() {
        return nextLvlXp;
    }

    public int getBaseAttackPower() {
        return baseAttackPower;
    }

    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public boolean getIncapacitated(){ return incapacitated; }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setRaceNum(int raceNum) {
        this.raceNum = raceNum;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setNextLvlXp(int nextLvlXp) {
        this.nextLvlXp = nextLvlXp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public void setBaseAttackPower(int baseAttackPower) {
        this.baseAttackPower = baseAttackPower;
    }

    public void setIncapacitated(Boolean incapacitated){
        this.incapacitated = incapacitated;
    }




}

//loadPlayerStats(): datasource class is sending the resultset from the character table in the SQLdatabase,
//                   as well as a string arraylist of the table's column names. This is to avoid hard-
//                   coding column names. The order of columns as they appear in the table/arraylist must be known.
//
//      public void loadPlayerStats (ResultSet stats, ArrayList<String> columnLabels) throws SQLException {
//
////      I designed the game to only allow one saved game at a time. So no need to figure which record to pull
////      from the resultset. Will revisit multiple saved games in future.
//        //stats.next();
//        super.setName(stats.getString(columnLabels.get(1)));
//        super.setDefense(stats.getInt(columnLabels.get(2)));
//        super.setAttackPower(stats.getInt(columnLabels.get(3)));
//        this.setBaseAttackPower(stats.getInt(columnLabels.get(4)));
//        super.setMaxHealth(stats.getInt(columnLabels.get(5)));
//        this.setCurrentHealth(stats.getInt(columnLabels.get(6)));
//        this.setBaseHealth(stats.getInt(columnLabels.get(7)));
//        this.setRace(stats.getString(columnLabels.get(8)));
//        this.setRaceNum(stats.getInt(columnLabels.get(9)));
//        this.setXp(stats.getInt(columnLabels.get(10)));
//        this.setNextLvlXp(stats.getInt(columnLabels.get(11)));
//        this.setLevel((stats.getInt(columnLabels.get(12))));
//        this.setXCoord(stats.getInt(columnLabels.get(13)));
//        this.setYCoord(stats.getInt(columnLabels.get(14)));
//
//
//    }
