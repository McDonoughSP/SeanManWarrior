package com.SeanMcDonough;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    Player player;
    TravelObj travelObj;
    public static final String DB_NAME = "SeanManWarrior.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Sean\\IdeaProjects\\SeanManWarrior\\" + DB_NAME;
    public static final String TABLE_CHARACTER = "character";
    public static final String TABLE_BOSS_KEYS = "boss_keys";

    public static final String COLUMN_BOSS_KEYS_NAME = "name";

    public static final String COLUMN_CHARACTER_ID = "_id";
    public static final String COLUMN_CHARACTER_NAME = "name";
    public static final String COLUMN_CHARACTER_DEFENSE = "defense";
    public static final String COLUMN_CHARACTER_ATTACK_POWER = "attack_power";
    public static final String COLUMN_CHARACTER_BASE_ATTACK_POWER = "base_attack_power";
    public static final String COLUMN_CHARACTER_MAX_HEALTH = "max_health";
    public static final String COLUMN_CHARACTER_CURRENT_HEALTH = "current_health";
    public static final String COLUMN_CHARACTER_BASE_HEALTH = "base_health";
    public static final String COLUMN_CHARACTER_RACE = "race";
    public static final String COLUMN_CHARACTER_RACE_NUM = "race_num";
    public static final String COLUMN_CHARACTER_XP = "xp";
    public static final String COLUMN_CHARACTER_NEXT_LVL_XP = "next_lvl_xp";
    public static final String COLUMN_CHARACTER_LEVEL = "level";
    public static final String COLUMN_CHARACTER_XCOORD = "x_coord";
    public static final String COLUMN_CHARACTER_YCOORD = "y_coord";

    public static final String INSERT_PLAYER_PREP = "INSERT INTO " + TABLE_CHARACTER + " (" +
            COLUMN_CHARACTER_NAME + ", " + COLUMN_CHARACTER_DEFENSE + ", " + COLUMN_CHARACTER_ATTACK_POWER +
            ", " + COLUMN_CHARACTER_BASE_ATTACK_POWER + ", " + COLUMN_CHARACTER_MAX_HEALTH +
            ", " + COLUMN_CHARACTER_CURRENT_HEALTH + ", " + COLUMN_CHARACTER_BASE_HEALTH + ", " + COLUMN_CHARACTER_RACE +
            ", " + COLUMN_CHARACTER_RACE_NUM + ", " + COLUMN_CHARACTER_XP + ", " + COLUMN_CHARACTER_NEXT_LVL_XP +
            ", " + COLUMN_CHARACTER_LEVEL + ", " + COLUMN_CHARACTER_XCOORD + ", " + COLUMN_CHARACTER_YCOORD + ")" +

            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_PLAYER_BOSS_KEY_PREP = "INSERT INTO " + TABLE_BOSS_KEYS + " (" +
            COLUMN_BOSS_KEYS_NAME + ") VALUES (?)";

    private PreparedStatement insertPlayer;
    private PreparedStatement insertPlayerBossKey;



    private Connection conn;
    public DataSource(){

    }
    public DataSource(Player player, TravelObj travelObj) {
        this.player = player;
        this.travelObj = travelObj;
    }

    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            insertPlayer = conn.prepareStatement(INSERT_PLAYER_PREP);
            insertPlayerBossKey = conn.prepareStatement(INSERT_PLAYER_BOSS_KEY_PREP);
            return true;
        } catch(SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {

            if(insertPlayer != null){
                insertPlayer.close();
            }
            if(insertPlayerBossKey != null){
                insertPlayerBossKey.close();
            }
            if(conn != null) {
                conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }
//    public void saveBossKey(String bossName){
//        try(Statement statement = conn.createStatement()){
//            statement.executeUpdate("INSERT INTO boss_keys VALUES('" + bossName + "');");
//        } catch(SQLException e) {
//            System.out.println("boss key save failed");
//        }
//    }

    public List<String> queryBossName() {
//
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_BOSS_KEYS)) {
            List<String> bossNames = new ArrayList<>();

            while (results.next()) {
                bossNames.add(results.getString("name"));
            }
            return bossNames;

        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
    //Only allowing one saved game for now. Will revisit later for multiple saves.
    public void insertPlayerToDataBase(){
        try(Statement statement = conn.createStatement()){
            conn.setAutoCommit(false);//starting transaction
            statement.executeUpdate("DELETE FROM " + TABLE_CHARACTER);//deletes any previous records before saving.
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CHARACTER);
            statement.executeUpdate("DELETE FROM " + TABLE_BOSS_KEYS);
            ResultSet results1 = statement.executeQuery("SELECT * FROM " + TABLE_BOSS_KEYS);
            if(results.next()) {
                System.out.println("Deletion of character failed.");
            } else if(results1.next()){
                System.out.println("Deletion of boss keys failed.");
            } else {
                insertPlayer.setString(1, player.getName());
                insertPlayer.setInt(2, player.getDefense());
                insertPlayer.setInt(3, player.getAttackPower());
                insertPlayer.setInt(4, player.getBaseAttackPower());
                insertPlayer.setInt(5, player.getMaxHealth());
                insertPlayer.setInt(6, player.getCurrentHealth());
                insertPlayer.setInt(7, player.getBaseHealth());
                insertPlayer.setString(8, player.getRaceString());
                insertPlayer.setInt(9, player.getRaceNum());
                insertPlayer.setInt(10, player.getXp());
                insertPlayer.setInt(11, player.getNextLvlXp());
                insertPlayer.setInt(12, player.getLevel());
                insertPlayer.setInt(13, player.getXCoord());
                insertPlayer.setInt(14, player.getYCoord());
                int affectedRows = insertPlayer.executeUpdate();
                if(affectedRows == 1){
                    conn.commit();
                    List<String> bossKeys = player.getBossKeyList();
                    if(bossKeys != null) {
                        for (int i = 0; i < bossKeys.size(); i++) {
                            insertPlayerBossKey.setString(1, bossKeys.get(i));
                            int affectedRows1 = insertPlayerBossKey.executeUpdate();
                            if(affectedRows1 == 1){
                                conn.commit();
                            } else {
                                System.out.println("BossKey save failed.");
                            }
                        }
                    }
                } else{
                    System.out.println("Character save failed.");
                }


            }

        } catch(SQLException e) {
            System.out.println("Error inserting to database." + e.getMessage());
            try{
                conn.rollback();
            }
            catch(SQLException e2){
                System.out.println("Could not perform rollback." + e2.getMessage());
            }
        } finally{
            try{
                conn.setAutoCommit(true);
            } catch(SQLException e){
                System.out.println("Could not set auto-commit to true." + e.getMessage());
            }

        }
    }
    public Player loadPlayer(){
        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_CHARACTER);
            Player player = setPlayerStats(resultSet);
            resultSet = statement.executeQuery("SELECT * FROM " + TABLE_BOSS_KEYS);
            player.setBossKeyList(getPlayerBossKeyList(resultSet));

            resultSet.close();
            statement.close();
            conn.close();
            return player;


        } catch(SQLException e){
            System.out.println("Error loading from database");
        }

        return null;
    }
//      Creates and returns a new player and sets stats from the database result set.
    private Player setPlayerStats(ResultSet resultSet) throws SQLException {
        Player player = new Player();
        player.setName(resultSet.getString(COLUMN_CHARACTER_NAME));
        player.setDefense(resultSet.getInt(COLUMN_CHARACTER_DEFENSE));
        player.setAttackPower(resultSet.getInt(COLUMN_CHARACTER_ATTACK_POWER));
        player.setBaseAttackPower(resultSet.getInt(COLUMN_CHARACTER_BASE_ATTACK_POWER));
        player.setMaxHealth(resultSet.getInt(COLUMN_CHARACTER_MAX_HEALTH));
        player.setCurrentHealth(resultSet.getInt(COLUMN_CHARACTER_CURRENT_HEALTH));
        player.setBaseHealth(resultSet.getInt(COLUMN_CHARACTER_BASE_HEALTH));
        player.setRace(resultSet.getString(COLUMN_CHARACTER_RACE));
        player.setRaceNum(resultSet.getInt(COLUMN_CHARACTER_RACE_NUM));
        player.setXp(resultSet.getInt(COLUMN_CHARACTER_XP));
        player.setNextLvlXp(resultSet.getInt(COLUMN_CHARACTER_NEXT_LVL_XP));
        player.setLevel(resultSet.getInt(COLUMN_CHARACTER_LEVEL));
        player.setXCoord(resultSet.getInt(COLUMN_CHARACTER_XCOORD));
        player.setYCoord(resultSet.getInt(COLUMN_CHARACTER_YCOORD));
        return player;
    }
    private List<String> getPlayerBossKeyList(ResultSet resultSet) throws SQLException {
        List<String> bossKeyList = new ArrayList<String>();
        while(resultSet.next()){
           bossKeyList.add(resultSet.getString(1));
        }
        return bossKeyList;
    }
//                  OLD CODE FOR INSERTING PLAYER INFO INTO DATABASE. USING PREPARED STATEMENTS NOW.
    //            statement.executeUpdate("INSERT INTO " + TABLE_CHARACTER + " (" +
//                    COLUMN_CHARACTER_NAME + ", " + COLUMN_CHARACTER_DEFENSE + ", " + COLUMN_CHARACTER_ATTACK_POWER +
//                    ", " + COLUMN_CHARACTER_BASE_ATTACK_POWER + ", " + COLUMN_CHARACTER_MAX_HEALTH +
//                    ", " + COLUMN_CHARACTER_CURRENT_HEALTH + ", " + COLUMN_CHARACTER_BASE_HEALTH + ", " + COLUMN_CHARACTER_RACE +
//                    ", " + COLUMN_CHARACTER_RACE_NUM + ", " + COLUMN_CHARACTER_XP + ", " + COLUMN_CHARACTER_NEXT_LVL_XP +
//                    ", " + COLUMN_CHARACTER_LEVEL + ", " + COLUMN_CHARACTER_XCOORD + ", " + COLUMN_CHARACTER_YCOORD + ")" +
//
//                    "VALUES ('" + player.getName() + "', " + player.getDefense() + ", " + player.getAttackPower() +
//                    ", " + player.getBaseAttackPower() + ", " + player.getMaxHealth() + ", " + player.getCurrentHealth() +
//                    ", " + player.getBaseHealth() + ", '" + player.getRaceString() + "', " + player.getRaceNum() +
//                    ", " + player.getXp() + ", " + player.getNextLvlXp() + ", " + player.getLevel() +
//                    ", " + travelObj.getX() + ", " + travelObj.getY() + ");");





    //*******************************************
    // NO LONGER USING PLAYER CLASS TO LOAD STATS FROM THE RESULTSET. USING SETPLAYERSTATS INSTEAD
    //To make the code more organized for the player.loadPlayerStats() method.
    //*******************************************
//
//    private ArrayList<String> createColumnArrayList(){
//        ArrayList<String> columnLabels = new ArrayList<String>();
//        columnLabels.add(COLUMN_CHARACTER_ID);
//        columnLabels.add(COLUMN_CHARACTER_NAME);
//        columnLabels.add(COLUMN_CHARACTER_DEFENSE);
//        columnLabels.add(COLUMN_CHARACTER_ATTACK_POWER);
//        columnLabels.add(COLUMN_CHARACTER_BASE_ATTACK_POWER);
//        columnLabels.add(COLUMN_CHARACTER_MAX_HEALTH);
//        columnLabels.add(COLUMN_CHARACTER_CURRENT_HEALTH);
//        columnLabels.add(COLUMN_CHARACTER_BASE_HEALTH);
//        columnLabels.add(COLUMN_CHARACTER_RACE);
//        columnLabels.add(COLUMN_CHARACTER_RACE_NUM);
//        columnLabels.add(COLUMN_CHARACTER_XP);
//        columnLabels.add(COLUMN_CHARACTER_NEXT_LVL_XP);
//        columnLabels.add(COLUMN_CHARACTER_LEVEL);
//        columnLabels.add(COLUMN_CHARACTER_XCOORD);
//        columnLabels.add(COLUMN_CHARACTER_YCOORD);
//
//        return columnLabels;
//    }

//    public List<Artist> queryArtists() {
//
//        try(Statement statement = conn.createStatement();
//            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS)) {
//
//
//            List<Artist> artists = new ArrayList<>();
//            while(results.next()) {
//                Artist artist = new Artist();
//                artist.setId(results.getInt(COLUMN_ARTIST_ID));
//                artist.setName(results.getString(COLUMN_ARTIST_NAME));
//                artists.add(artist);
//            }
//
//            return artists;
//
//
//        } catch(SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//            return null;
//        }
//    }
}
