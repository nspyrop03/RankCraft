package me.amc.rankcraft.stats;

import me.amc.rankcraft.MainCore;
import me.amc.rankcraft.utils.RCUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class SaveSystem {

     private HashMap<String, PlayerStats> psMap;
     private boolean useDb;

     public SaveSystem() {
          psMap = new HashMap<>();
          useDb = MainCore.instance.config.enableDb;
     }

     public void initForPlayer(Player p) {
          String uuid = RCUtils.textedUUID(p);
          if(!psMap.containsKey(uuid)) {
               PlayerStats ps = new PlayerStats(uuid, 0, 1, 0, 0, 0, 0, 0);
               psMap.put(uuid, ps);
               if(useDb) {
                    try {
                         MainCore.instance.statsDatabase.createPlayerStats(uuid, ps);
                    } catch (SQLException e) {
                         e.printStackTrace();
                    }
               }
          }
     }

     public void setPlayerStats(String uuid, PlayerStats ps) {
          psMap.put(uuid, ps);
     }

     public PlayerStats getPlayerStats(String uuid) {
          return psMap.get(uuid);
     }

     // Write data to file or to database
     public void save() {
          if(useDb) {
               try {
                    saveToCloud(psMap);
               } catch (SQLException ex) {
                    MainCore.instance.getLogger().log(Level.SEVERE, "SaveSystem: Cloud Save Failed!", ex);
               }
          } else {
               try {
                    saveToLocal(psMap);
               } catch (Exception ex) {
                    MainCore.instance.getLogger().log(Level.SEVERE, "SaveSystem: Local Save Failed!", ex);
               }
          }

     }

     // Load data from file or from database
     public void load(){
          if(useDb) {
               try {
                    psMap = loadFromCloud();
               } catch (SQLException e) {
                    e.printStackTrace();
               }
          } else {
               try {
                    psMap = loadFromLocal();
               } catch (Exception ex) {
                    MainCore.instance.getLogger().log(Level.SEVERE, "SaveSystem: Load Failed!", ex);
               }
          }
     }

     private void saveToLocal(HashMap<String, PlayerStats> toLocal) throws IOException {
          YamlConfiguration stats = new YamlConfiguration();
          stats.createSection("playerStats", toLocal);
          stats.save(new File(RCUtils.STATS_DIRECTORY, "stats.yml"));
     }

     private HashMap<String, PlayerStats> loadFromLocal() throws IOException, InvalidConfigurationException {
          HashMap<String, PlayerStats> local = new HashMap<>();

          File stats = new File(RCUtils.STATS_DIRECTORY, "stats.yml");
          if (stats.exists()) {
               YamlConfiguration config = new YamlConfiguration();
               config.load(stats);

               ConfigurationSection section = config.getConfigurationSection("playerStats");
               for (String key : section.getKeys(false)) {
                    PlayerStats ps = (PlayerStats) section.get(key);
                    local.put(key, ps);
               }
          }
          return local;

     }

     private void saveToCloud(HashMap<String, PlayerStats> toCloud) throws SQLException {
          for(String uuid : toCloud.keySet())
               MainCore.instance.statsDatabase.updatePlayerStats(uuid, toCloud.get(uuid));
     }

     private HashMap<String, PlayerStats> loadFromCloud() throws SQLException {
          HashMap<String, PlayerStats> cloud = new HashMap<>();
          List<PlayerStats> psList = MainCore.instance.statsDatabase.findAllPlayerStats();
          for(PlayerStats ps : psList) {
               cloud.put(ps.getUUID(), ps);
          }
          return cloud;
     }

     // Copy local data to cloud
     public void syncCloud() throws IOException, InvalidConfigurationException, SQLException {
          HashMap<String, PlayerStats> map = loadFromLocal();
          saveToCloud(map);
          psMap = map;
     }

     // Copy cloud data to local
     public void syncLocal() throws SQLException, IOException {
          HashMap<String, PlayerStats> map = loadFromCloud();
          saveToLocal(map);
          psMap = map;
     }
}
