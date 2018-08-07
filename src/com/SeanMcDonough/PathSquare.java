package com.SeanMcDonough;

public class PathSquare extends MapSquare {
    public PathSquare(Event event, int X, int Y, boolean isAccessible, RandomMonster randomMonster) {
        super(event, X, Y, isAccessible, randomMonster);
    }
    @Override
    public void description(){
        System.out.println("You walk along the path between the trees.");
    }
}
