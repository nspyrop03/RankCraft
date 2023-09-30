package me.amc.rankcraft;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHelper {

     private FileConfiguration config;

     // Database variables
     public String dbHost;
     public String dbPort;
     public String dbDatabase;
     public String dbUser;
     public String dbPassword;
     public boolean enableDb;

     public ConfigHelper(FileConfiguration config) {
          this.config = config;
          initVariables();
     }

     private void initVariables() {
          dbHost = config.getString("Database.host");
          dbPort = config.getString("Database.port");
          dbDatabase = config.getString("Database.database");
          dbUser = config.getString("Database.user");
          dbPassword = config.getString("Database.password");
          enableDb = config.getBoolean("EnableDatabase");
     }

}
