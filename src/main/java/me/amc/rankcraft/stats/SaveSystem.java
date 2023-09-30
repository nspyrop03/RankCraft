package me.amc.rankcraft.stats;

import me.amc.rankcraft.MainCore;
import me.amc.rankcraft.utils.RCUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
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

     // Write data to file or to database
     public void save() {
          if(useDb) {
               for(String uuid : psMap.keySet()) {
                    try {
                         MainCore.instance.statsDatabase.updatePlayerStats(uuid, psMap.get(uuid));
                    } catch (SQLException e) {
                         e.printStackTrace();
                    }
               }
          } else {
               try {
                    YamlConfiguration stats = new YamlConfiguration();
                    stats.createSection("playerStats", psMap);
                    stats.save(new File(RCUtils.STATS_DIRECTORY, "stats.yml"));

               } catch (Exception ex) {
                    MainCore.instance.getLogger().log(Level.SEVERE, "SaveSystem: Save Failed!", ex);
               }
          }

     }

     // Load data from file or from database
     public void load(){
          if(useDb) {
               try {
                    List<PlayerStats> psList = MainCore.instance.statsDatabase.findAllPlayerStats();
                    for(PlayerStats ps : psList) {
                         psMap.put(ps.getUUID(), ps);
                    }
               } catch (SQLException e) {
                    e.printStackTrace();
               }
          } else {
               try {
                    File stats = new File(RCUtils.STATS_DIRECTORY, "stats.yml");
                    if (stats.exists()) {
                         YamlConfiguration config = new YamlConfiguration();
                         config.load(stats);

                         ConfigurationSection section = config.getConfigurationSection("playerStats");
                         for (String key : section.getKeys(false)) {
                              PlayerStats ps = (PlayerStats) section.get(key);
                              psMap.put(key, ps);
                         }

                    }
               } catch (Exception ex) {
                    MainCore.instance.getLogger().log(Level.SEVERE, "SaveSystem: Load Failed!", ex);
               }
          }
     }

}
