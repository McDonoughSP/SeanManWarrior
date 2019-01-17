package com.SeanMcDonough;

import java.util.ArrayList;
import java.util.Scanner;

public class TravelObj {

    private Map map;
    private Printer printer;
    private MapSquare mapSquare;
    private Player player;


    public TravelObj(Map map, Player player) {
        this.player = player;
        this.map = map;
        player.setXCoord(map.getStartX());
        player.setYCoord(map.getStartY());
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
                    System.out.println("X: " + player.getXCoord() + "\n" +
                                       "Y: " + player.getYCoord());
                    break;
                case "7":
                    printer.printMap(map, player.getXCoord(), player.getYCoord());
                    break;
                case "0":
                    quit = true;
                    printer.printMainMenu();
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
        this.mapSquare = map.findMapSquare((player.getXCoord()+1), player.getYCoord());
        if(mapSquare != null) {
            mapSquare.description();
            if (mapSquare.isAccessible()) {
                player.setXCoord((player.getXCoord()+1));
                travelAction(mapSquare);
            }
        } else{
            System.out.println("Your path is blocked.");
        }
    }
    public void moveEast(){
        this.mapSquare = map.findMapSquare((player.getXCoord()), (player.getYCoord()+1));
        if(mapSquare != null) {
            mapSquare.description();
            if (mapSquare.isAccessible()) {
                player.setYCoord((player.getYCoord()+1));
                travelAction(mapSquare);
            }
        } else{
            System.out.println("Your path is blocked.");
        }
    }

    public void moveSouth(){
        this.mapSquare = map.findMapSquare((player.getXCoord()-1), player.getYCoord());
        if(mapSquare != null) {
            mapSquare.description();
            if (mapSquare.isAccessible()) {
                player.setXCoord((player.getXCoord()-1));
                travelAction(mapSquare);
            }
        } else{
            System.out.println("Your path is blocked.");
        }
    }
    public void moveWest(){
        this.mapSquare = map.findMapSquare((player.getXCoord()), (player.getYCoord()-1));
        if(mapSquare != null) {
            mapSquare.description();
            if (mapSquare.isAccessible()) {
                player.setYCoord((player.getYCoord()-1));
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
                mapSquare.setPlayer(player);//may be better than initializing with constructor since its not always needed.
                mapSquare.setMonster(mapSquare.randomMonster());
                mapSquare.battle(map.getStartX(), map.getStartY());
            }
        }
    }

    private void randomMonster(ArrayList<Monster> monsterArrayList){

    }
}
