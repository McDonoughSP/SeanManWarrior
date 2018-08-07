package com.SeanMcDonough;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TravelObj {
    private int X;
    private int Y;
    private Map map;
    private Printer printer;
    private MapSquare mapSquare;


    public TravelObj(Map map) {
        X = map.getStartX();
        Y = map.getStartY();
        this.map = map;
        this.printer = new Printer();
    }
    public void travel(){
        Scanner scanner = new Scanner(System.in);
        String choice;
        boolean quit = false;
        System.out.println("Now Traveling.");
        while(!quit) {
            printer.printTravelMenu();
            System.out.println("Make your selection:");
            choice = scanner.nextLine();
            switch (choice) {
                case "8":
                    System.out.println("Moving North");
                    moveNorth();
                    break;
                case "6":
                    System.out.println("Moving East");
                    moveEast();
                    break;
                case "2":
                    System.out.println("Moving South");
                    moveSouth();
                    break;
                case "4":
                    System.out.println("Moving West");
                    moveWest();
                    break;
                case "5":
                    System.out.println("X: " + X + "\n" +
                                       "Y: " + Y);
                    break;
                case "7":
                    printer.printMap(map, X, Y);
                    break;
                case "0":
                    quit = true;
                    break;
                case "9":
                    printer.printTravelMenu();
                    break;
                default:
                    break;
            }
        }
    }

    public void moveNorth(){
        MapSquare mapSquare = map.findMapSquare((X+1), Y);
        if(mapSquare != null) {
            mapSquare.description();
            if (mapSquare.isAccessible()) {
                this.X++;
                travelAction(mapSquare);
            }
        } else{
            System.out.println("Your path is blocked.");
        }
    }
    public void moveEast(){
        MapSquare mapSquare = map.findMapSquare((X), (Y+1));
        if(mapSquare != null) {
            mapSquare.description();
            if (mapSquare.isAccessible()) {
                this.Y++;
                travelAction(mapSquare);
            }
        } else{
            System.out.println("Your path is blocked.");
        }
    }

    public void moveSouth(){
        MapSquare mapSquare = map.findMapSquare((X-1), Y);
        if(mapSquare != null) {
            mapSquare.description();
            if (mapSquare.isAccessible()) {
                this.X--;
                travelAction(mapSquare);
            }
        } else{
            System.out.println("Your path is blocked.");
        }
    }
    public void moveWest(){
        MapSquare mapSquare = map.findMapSquare((X), (Y-1));
        if(mapSquare != null) {
            mapSquare.description();
            if (mapSquare.isAccessible()) {
                this.Y--;
                travelAction(mapSquare);
            }
        } else{
            System.out.println("Your path is blocked.");
        }
    }

    public void travelAction(MapSquare mapSquare){
        if(mapSquare.hasEvent()) {
            mapSquare.action();
        } else{
            if(mapSquare.randomMonsterEncounter()){
                mapSquare.setPlayer(map.getPlayer());//may be better than initializing with constructor since its not always needed.
                mapSquare.setMonster(mapSquare.randomMonster());
                mapSquare.battle();
            }
        }
    }

    private void randomMonster(ArrayList<Monster> monsterArrayList){

    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }
}
