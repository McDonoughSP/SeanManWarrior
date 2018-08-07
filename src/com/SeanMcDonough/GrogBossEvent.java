package com.SeanMcDonough;
import java.sql.*;
import java.util.List;

public class GrogBossEvent implements Event {
    //private Connection conn;
    //DataSource dataSource = new DataSource();
    Player player;
     public GrogBossEvent(Player player){
        this.player = player;
        }
        public void description(){
         if(!player.findBossKey("grog")) {
             System.out.println("Grog looks hungry...");
         }

        }
        public void action(){
         if(!player.findBossKey("grog")) {
             Monster grog = new GrogBoss();
             Battle battle = new Battle(grog, player);
             battle.run();
         }
        }

//        public boolean queryBossKey(String bossName){
//         dataSource.open();
//         List<String> bossNames = dataSource.queryBossName();
//
//         for(int i = 0; i<bossNames.size(); i++){
//             if(bossNames.get(i).equals(bossName)){
//                 return true;
//             }
//         }
//         dataSource.close();
//         return false;
//        }



}
