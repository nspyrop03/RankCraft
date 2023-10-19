package me.amc.rankcraft.stats;

import me.amc.rankcraft.config.ConfigHelper;
import me.amc.rankcraft.MainCore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatsDatabase {

     private Connection connection;
     private ConfigHelper config;

     public StatsDatabase() throws SQLException {
          config = MainCore.instance.config;
          String url = "jdbc:mysql://"+config.dbHost+":"+config.dbPort+"/"+config.dbDatabase;
          String user = config.dbUser;
          String password = config.dbPassword;

          this.connection = DriverManager.getConnection(url, user, password);

          MainCore.instance.getLogger().info("Connected to database!");
     }

     public void initDatabase() throws SQLException {
          Statement statement = this.connection.createStatement();
          String sql = "CREATE TABLE IF NOT EXISTS player_stats (uuid VARCHAR(36) primary key, " +
                  "exp FLOAT(2), level INT, gold FLOAT(2), blocksPlaced INT, blocksBroken INT, " +
                  "mana INT, maxMana INT)";
          statement.execute(sql);
          statement.close();
     }

     public List<PlayerStats> findAllPlayerStats() throws SQLException {
          PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM player_stats");
          ResultSet rs = statement.executeQuery();
          List<PlayerStats> psList = new ArrayList<>();
          while(rs.next()) {
               psList.add(new PlayerStats(
                       rs.getString("uuid"),
                       rs.getFloat("exp"),
                       rs.getInt("level"),
                       rs.getFloat("gold"),
                       rs.getInt("blocksPlaced"),
                       rs.getInt("blocksBroken"),
                       rs.getInt("mana"),
                       rs.getInt("maxMana")));
          }
          statement.close();
          return psList;
     }

     public PlayerStats findPlayerStatsByUUID(String uuid) throws SQLException {
          PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM player_stats WHERE uuid = ?");
          statement.setString(1, uuid);

          ResultSet rs = statement.executeQuery();
          PlayerStats playerStats;

          if(rs.next()){
               playerStats = new PlayerStats(
                       rs.getString("uuid"),
                       rs.getFloat("exp"),
                       rs.getInt("level"),
                       rs.getFloat("gold"),
                       rs.getInt("blocksPlaced"),
                       rs.getInt("blocksBroken"),
                       rs.getInt("mana"),
                       rs.getInt("maxMana"));

               statement.close();
               return playerStats;
          }

          statement.close();
          return null;
     }

     public void createPlayerStats(String uuid, PlayerStats ps) throws SQLException {
          PreparedStatement statement = this.connection.prepareStatement(
                  "INSERT INTO player_stats(uuid, exp, level, gold, blocksPlaced, blocksBroken," +
                          " mana, maxMana) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
          statement.setString(1, uuid);
          statement.setFloat(2, ps.getExp());
          statement.setInt(3, ps.getLevel());
          statement.setFloat(4, ps.getGold());
          statement.setInt(5, ps.getBlocksPlaced());
          statement.setInt(6, ps.getBlocksBroken());
          statement.setInt(7, ps.getMana());
          statement.setInt(8, ps.getMaxMana());

          statement.executeUpdate();
          statement.close();
     }

     public void updatePlayerStats(String uuid, PlayerStats ps) throws SQLException {
          PreparedStatement statement = this.connection.prepareStatement(
                  "UPDATE player_stats SET exp = ?, level = ?, gold = ?, blocksPlaced = ?, " +
                          "blocksBroken = ?, mana = ?, maxMana = ? WHERE uuid = ?");
          statement.setFloat(1, ps.getExp());
          statement.setInt(2, ps.getLevel());
          statement.setFloat(3, ps.getGold());
          statement.setInt(4, ps.getBlocksPlaced());
          statement.setInt(5, ps.getBlocksBroken());
          statement.setInt(6, ps.getMana());
          statement.setInt(7, ps.getMaxMana());
          statement.setString(8, uuid);

          statement.executeUpdate();
          statement.close();
     }

     public void deletePlayerStats(String uuid) throws SQLException {
          PreparedStatement statement = this.connection.prepareStatement("DELETE FROM player_stats WHERE uuid = ?");
          statement.setString(1, uuid);
          statement.executeUpdate();
          statement.close();
     }

}
