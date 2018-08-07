package com.SeanMcDonough;

import java.util.ArrayList;

public class Map1 implements Map {
    private int startX;
    private int startY;
    private Player player;
    private ArrayList<MapSquare> mapSquareArrayList;

    public Map1(Player player) {
        this.player = player;
        createMap();
    }
    @Override
    public MapSquare findMapSquare(int x, int y){
        for(int i=0; i<mapSquareArrayList.size(); i++){
            int xx= mapSquareArrayList.get(i).getCoord().getX();
            int yy= mapSquareArrayList.get(i).getCoord().getY();
            if(x == xx && y == yy){
                return mapSquareArrayList.get(i);
            }

        }
        return null;
    }



    public void printMap(int x, int y){
        char[][] mapArray = createMapArray();
        mapArray[x][y] = 'X';


        for(int i = mapArray.length; i>0; i--){
            for(int j = 0; j<mapArray[i-1].length; j++){
                System.out.print(mapArray[i-1][j] + " ");

            }
            System.out.println();

        }

    }
    public char[][] createMapArray(){
        char[] array1 = new char[6];
        array1[0] = '*';
        array1[1] = '*';
        array1[2] = '*';
        array1[3] = '*';
        array1[4] = '*';
        array1[5] = '*';
        char[] array2 = new char[6];
        array2[0] = '*';
        array2[1] = '*';
        array2[2] = '*';
        array2[3] = '*';
        array2[4] = '*';
        array2[5] = '*';
        char[] array3 = new char[6];
        array3[0] = '*';
        array3[1] = '*';
        array3[2] = '*';
        array3[3] = '*';
        array3[4] = '*';
        array3[5] = '*';
        char[] array4 = new char[6];
        array4[0] = '*';
        array4[1] = '*';
        array4[2] = '*';
        array4[3] = '*';
        array4[4] = '*';
        array4[5] = '*';
        char[] array5 = new char[6];
        array5[0] = '*';
        array5[1] = '*';
        array5[2] = '*';
        array5[3] = '*';
        array5[4] = '*';
        array5[5] = '*';
        char[] array6 = new char[6];
        array6[0] = '*';
        array6[1] = '*';
        array6[2] = '*';
        array6[3] = '*';
        array6[4] = '*';
        array6[5] = '*';
        char[][] yArray = new char[][] {array1, array2, array3, array4, array5, array6};
        return yArray;

    }

    @Override
    public int getStartX() {
        return startX;
    }

    @Override
    public int getStartY() {
        return startY;
    }
    public Player getPlayer(){
        return player;
    }



    @Override
    public void createMap(){
        this.mapSquareArrayList = new ArrayList<>();
        this.startX = 0;
        this.startY = 0;
        RandomMonsterCreator monsterCreator = new RandomMonsterCreator();
        MapSquare mapSquare;




        mapSquare = new PathSquare(null, 0, 0, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 0, 1, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 0, 2, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 0, 3, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 0, 4, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 0, 5, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);

        mapSquare = new PathSquare(null, 1, 0, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 1, 1, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 1, 2, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 1, 3, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 1, 4, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 1, 5, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);

        mapSquare = new PathSquare(null, 2, 0, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 2, 1, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 2, 2, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 2, 3, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 2, 4, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 2, 5, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);

        mapSquare = new PathSquare(null, 3, 0, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 3, 1, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 3, 2, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 3, 3, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 3, 4, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 3, 5, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);

        mapSquare = new PathSquare(null, 4, 0, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 4, 1, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 4, 2, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 4, 3, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 4, 4, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 4, 5, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);

        mapSquare = new PathSquare(null, 5, 0, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 5, 1, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 5, 2, true, monsterCreator.createLevelOne());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 5, 3, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(null, 5, 4, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);
        mapSquare = new PathSquare(new GrogBossEvent(player), 5, 5, true, monsterCreator.createLevelTwo());
        mapSquareArrayList.add(mapSquare);



    }

}

