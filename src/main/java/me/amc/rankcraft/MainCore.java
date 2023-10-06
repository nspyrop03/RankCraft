package me.amc.rankcraft;

import me.amc.rankcraft.commands.RankCraftCommand;
import me.amc.rankcraft.stats.StatsDatabase;
import org.bukkit.plugin.java.JavaPlugin;

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
          reloadConfig();

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
