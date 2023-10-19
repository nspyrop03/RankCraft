package me.amc.rankcraft;

import me.amc.rankcraft.commands.RankCraftCommand;
import me.amc.rankcraft.config.ConfigHelper;
import me.amc.rankcraft.config.ConfigUpdater;
import me.amc.rankcraft.stats.StatsDatabase;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class MainCore extends JavaPlugin {

     public static MainCore instance; // Singleton

     public ConfigHelper config;
     public RankCraft rankCraft;
     public PermissionList permList;

     public StatsDatabase statsDatabase;

     @Override
     public void onEnable() {
          instance = this;
          saveDefaultConfig();
          //No Format no comments
          //getConfig().options().copyDefaults(true);
          //saveConfig();
          /*
          InputStream customClassStream= this.getClass().getResourceAsStream("/config.yml");
          InputStreamReader strR = new InputStreamReader(customClassStream);
          FileConfiguration defaults = YamlConfiguration.loadConfiguration(strR);
          System.out.println("test: "+defaults.contains("testNewField"));
          */
          reloadConfig();
          /*
          System.out.println("Test: "+getConfig().get("testNewField"));
          System.out.println("Test 2: "+getConfig().saveToString());
          */

          ConfigUpdater cu = new ConfigUpdater();
          try {
               cu.update();
          } catch (IOException e) {
               e.printStackTrace();
          }

          if(config.enableDb) {
               try {
                    statsDatabase = new StatsDatabase();
                    statsDatabase.initDatabase();
               } catch (SQLException e) {
                    e.printStackTrace();
                    getLogger().warning("Could not init StatsDatabase!");
               }
          }

          permList = new PermissionList();

          rankCraft = new RankCraft();
          rankCraft.saveSystem.load();
          rankCraft.registerEvents();

          rankCraft.initSubCommands();
          getCommand("rankcraft").setExecutor(new RankCraftCommand());
     }

     @Override
     public void onDisable() {
          rankCraft.saveSystem.save();
     }

     @Override
     public void reloadConfig() {
          super.reloadConfig();
          config = new ConfigHelper(this.getConfig());
     }


}
