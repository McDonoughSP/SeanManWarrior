package com.SeanMcDonough;

public class GrogBoss extends Monster {

    public GrogBoss(){
        super("Grog", 60, 18, 15, 60, 300);

    }

    public void specialAction(Player player){
        System.out.println("Grog breaths fire!");
        int damage = (25 - player.getDefense());
        dealDamage(damage, player);
    }

    public void getLoot(Player player){
        if(!player.findBossKey("grog")) {
            player.saveBossKey("grog");
        }
    }

//    public void saveKey(){
//        DataSource dataSource = new DataSource();
//        if(!dataSource.open()){
//            System.out.println("Cannot open data source. Key save failed.");
//        }
//        dataSource.saveBossKey("Grog");
//    }
}
