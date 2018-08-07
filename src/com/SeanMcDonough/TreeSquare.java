package com.SeanMcDonough;

public class TreeSquare extends MapSquare {

    public TreeSquare(Event event, int X, int Y, boolean isAccessible, RandomMonster randomMonster) {
        super(event, X, Y, isAccessible, randomMonster);
    }
    @Override
    public void description(){
        System.out.println("There are too many trees blocking your path.");
    }

}

