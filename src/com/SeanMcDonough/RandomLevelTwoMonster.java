package com.SeanMcDonough;

import java.util.Random;

public class RandomLevelTwoMonster implements RandomMonster{
    public RandomLevelTwoMonster() {
    }

    public Monster generateMonster(){
        Random rand = new Random();
        int randNum = rand.nextInt(3);
        switch(randNum){
            case 0:
                return new Ghost();
            case 1:
                return new Ghost();
            case 2:
                return new Ghost();
            default:
                break;
        }
        return null;
    }

}
