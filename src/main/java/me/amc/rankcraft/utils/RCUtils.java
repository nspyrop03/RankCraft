package me.amc.rankcraft.utils;

import org.bukkit.entity.Player;

import java.io.File;

public class RCUtils {

     public static final File STATS_DIRECTORY = new File("plugins/RankCraft/stats");

     public static String textedUUID(Player p) {
          return p.getUniqueId().toString();
     }

}
