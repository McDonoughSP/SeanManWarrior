package com.SeanMcDonough;

import java.util.ArrayList;

public interface Map {
    public int getStartX();
    public int getStartY();
    public MapSquare findMapSquare(int x, int y);
    public void createMap();
    public Player getPlayer();
    public void printMap(int x, int y);

}
