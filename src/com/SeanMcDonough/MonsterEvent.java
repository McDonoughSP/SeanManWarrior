package com.SeanMcDonough;

public class MonsterEvent implements Event{
    private Battle battle;
    private Monster monster;
    private Player player;

    MonsterEvent(Monster monster, Player player){
        this.monster = monster;
        this.player = player;
        this.battle = new Battle(monster, player);

    }

    @Override
    public void description() {
        System.out.println("A monster appears!");
    }
    @Override
    public void action(){
        System.out.println("Battle phase!");
        battle.run();
    }
}
