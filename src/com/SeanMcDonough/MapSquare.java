package com.SeanMcDonough;

import java.util.Random;

public class MapSquare {
    private boolean isAccessible;
    private Event event;
    private Coord coord;
    private Player player;
    private Monster monster;
    private RandomMonster randomMonster;

    public MapSquare(Event event, int X, int Y, boolean isAccessible, RandomMonster randomMonster){
        this.coord = new Coord(X, Y);
        this.event = event;
        this.isAccessible = isAccessible;
        this.randomMonster = randomMonster;
    }
    public void action(){
        if(event != null) {
            event.action();
        }
    }
    public void setPlayer(Player player){
        this.player = player;
    }
    public void setMonster(Monster monster){
        this.monster = monster;
    }
    public void addEvent(Event event){
        this.event = event;
    }
    public boolean hasEvent(){
        if(event != null){
            return true;
        }
        return false;
    }
    public boolean randomMonsterEncounter(){
        Random rand = new Random();
        int randNum = rand.nextInt(13);
        if (randNum < 13) {
            return true;
        }
        return false;
    }
    public Monster randomMonster(){
        return randomMonster.generateMonster();
    }

    public void battle(){
        Battle battle = new Battle(monster, player);
        battle.run();
    }
    public void description(){}


    public boolean isAccessible() {
        return isAccessible;
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Player getPlayer() {
        return player;
    }

    public Monster getMonster() {
        return monster;
    }

    public RandomMonster getRandomMonster() {
        return randomMonster;
    }

    public void setRandomMonster(RandomMonster randomMonster) {
        this.randomMonster = randomMonster;
    }

    public Event getEvent() {
        return event;
    }

    public Coord getCoord() {
        return coord;
    }

}
