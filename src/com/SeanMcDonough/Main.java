package com.SeanMcDonough;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {




        ///Starting game
        startMenu();






    }

    public static void startMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Welcome to Sean Man Dungeon!");
        System.out.println("Choose one:" +
                "1 - Load saved game." +
                "2 - Start new game.");

        choice = scanner.nextInt();
        switch(choice){
            case 1:
                loadGame();
                break;
            case 2:
                createGame();

        }
    }

    public static Player createPlayer(){
        String name;
        int choice;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name:");
        name = scanner.nextLine();
        System.out.println("Choose your race:\n" +
                "1 - Human\n" +
                "2 - Elf");
        choice = scanner.nextInt();
        Player player = new Player(name, choice);
        return player;
    }


    //Only player stats and x/y coords are loading at the moment. I have not created any other objects that need to be loaded.
    public static void loadGame(){
        DataSource dataSource = new DataSource();
        if(!dataSource.open()){
            System.out.println("Cannot open datasource. Save failed.");
            return;
        }
        Player player = dataSource.loadPlayer();//creates a new player with all the stats loaded from the database
        Map map1 = new Map1(player);
        MainMenu mainMenu = new MainMenu(player, map1);
        //setting the travel object x/y. may be better to write a separate method for the travelobj
        //to load from the database itself. for now it all loads to the player. the travelObj
        //uses the x/y to move around the map.
        mainMenu.setTravelObjCoords(player.getXCoord(), player.getYCoord());
        mainMenu.run();


    }
    public static void createGame(){
        Player player = createPlayer();
        Map map1 = new Map1(player);
        MainMenu mainMenu = new MainMenu(player, map1);
        mainMenu.run();
    }



}
