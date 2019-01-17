package com.SeanMcDonough;
public class GrogBossEvent implements Event {
    //private Connection conn;
    //DataSource dataSource = new DataSource();
    Player player;
    private int startX;
    private int startY;

     public GrogBossEvent(Player player, int startX, int startY){
        this.player = player;
        this.startX = startX;
        this.startY = startY;
        }
        public void description(){
         if(!player.findBossKey("grog")) {
             System.out.println("Grog looks hungry...");
         }

        }
        public void action(){
         if(!player.findBossKey("grog")) {
             Monster grog = new GrogBoss();
             Battle battle = new Battle(grog, player, startX, startY);
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
