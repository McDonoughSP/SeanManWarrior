package com.SeanMcDonough;

import java.util.Scanner;


public class MainMenu {

    private TravelObj travelObj;
    private Player player;
    private Printer printer;




    public MainMenu(Player player, Map map) {
        this.player = player;
        this.printer = new Printer();
        this.travelObj = new TravelObj(map);
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        String choice;
        boolean quit = false;
        printer.printIntro();
        while(!quit) {
            printer.printMainMenu();
            System.out.println("Make your selection:");
            choice = scanner.nextLine();
            switch (choice) {
                case "q":
                    System.out.println("Quiting game. Farewell.");
                    quit = true;
                    break;
                case "t":
                    travelObj.travel();
                    break;
                case "r":
                    rest();
                    break;
                case "s":
                    spells();
                    break;
                case "c":
                    System.out.println(player.getName());
                    printer.printCharacterStats(player);
                    boolean back = false;
                    System.out.print("Press enter");
                    while(!back){
                       String input = scanner.nextLine();
                       if(input != null){
                           back = true;
                       }
                    }
                    break;
                case "p":
                    printer.printMainMenu();
                    break;
                case "v":
                    savePlayer(player, travelObj);
                    break;
                default:
                    break;


            }
        }
    }
    public static void savePlayer(Player player, TravelObj travelObj){
        DataSource dataSource = new DataSource(player, travelObj);

        if(!dataSource.open()){
            System.out.println("Cannot open datasource. Save failed.");
            return;
        }
        dataSource.insertPlayerToDataBase();
        dataSource.close();

    }
    private void spells(){
        System.out.println("Casting Spells");
    }
    private void rest(){
        this.player.recoverHealth(5);
        System.out.println("Recovered 5 health");
    }

    public void setTravelObjCoords(int x, int y){
        travelObj.setX(x);
        travelObj.setY(y);
    }


}
