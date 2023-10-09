package me.amc.rankcraft;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigHelper {

     private FileConfiguration config;

     // Database variables
     public String dbHost;
     public String dbPort;
     public String dbDatabase;
     public String dbUser;
     public String dbPassword;
     public boolean enableDb;

     // Exp options
     public float expPlaceDefault;
     public float expBreakDefault;
     public List<String> expPlaceSpecialList;
     public List<String> expBreakSpecialList;
     public List<String> noExpList;
     public boolean saveBlocks;
     public int blocksToSave;
     private List<Material> noExpMaterialList;
     private HashMap<Material, Float> expSpecialPlaceMap;
     private HashMap<Material, Float> expSpecialBreakMap;

     public ConfigHelper(FileConfiguration config) {
          this.config = config;
          initVariables();
          initExpLists();
     }

     private void initVariables() {
          dbHost = config.getString("Database.host");
          dbPort = config.getString("Database.port");
          dbDatabase = config.getString("Database.database");
          dbUser = config.getString("Database.user");
          dbPassword = config.getString("Database.password");
          enableDb = config.getBoolean("EnableDatabase");

          expPlaceDefault = (float)config.getDouble("ExpGain.Default.Place");
          expBreakDefault = (float)config.getDouble("ExpGain.Default.Break");
          expPlaceSpecialList = config.getStringList("ExpGain.Special.Place");
          expBreakSpecialList = config.getStringList("ExpGain.Special.Break");
          noExpList = config.getStringList("ExpGain.NoExp");
          saveBlocks = config.getBoolean("SaveBlocks");
          blocksToSave = config.getInt("BlocksToSave");
     }

     private void initExpLists() {
          noExpMaterialList = new ArrayList<>();
          for(String s : noExpList)
               noExpMaterialList.add(Material.valueOf(s));

          expSpecialPlaceMap = new HashMap<>();
          for(String s : expPlaceSpecialList) {
               String[] parts = s.split(":");
               expSpecialPlaceMap.put(Material.valueOf(parts[0]), Float.valueOf(parts[1]));
          }

          expSpecialBreakMap = new HashMap<>();
          for(String s : expBreakSpecialList) {
               String[] parts = s.split(":");
               expSpecialBreakMap.put(Material.valueOf(parts[0]), Float.valueOf(parts[1]));
          }
     }

     public List<Material> getNoExpMaterialList() {
          return this.noExpMaterialList;
     }

     public HashMap<Material, Float> getExpSpecialMap(boolean place) {
          return place ? this.expSpecialPlaceMap : this.expSpecialBreakMap;
     }

}
