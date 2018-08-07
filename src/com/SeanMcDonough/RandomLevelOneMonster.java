package com.SeanMcDonough;

import java.util.Random;

public class RandomLevelOneMonster implements RandomMonster {
    public RandomLevelOneMonster(){

    }

    public Monster generateMonster(){
        Random rand = new Random();
        int randNum = rand.nextInt(3);
        switch(randNum){
            case 0:
                return new Slime();
            case 1:
                return new RedSlime();
            case 2:
                return new BlueSlime();
            default:
                break;
        }
        return null;
    }
}

