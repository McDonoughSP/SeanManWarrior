package com.SeanMcDonough;
//creates a random monster of a certain level
public class RandomMonsterCreator {
    public RandomMonsterCreator() {
    }

    public RandomMonster createLevelOne(){
        return new RandomLevelOneMonster();
    }

    public RandomMonster createLevelTwo() { return new RandomLevelTwoMonster(); }
}
