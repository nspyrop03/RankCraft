package me.amc.rankcraft.utils;

import me.amc.rankcraft.MainCore;
import me.amc.rankcraft.rpgitem.Rarity;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.io.File;

public class RCUtils {

     public static final File STATS_DIRECTORY = new File("plugins/RankCraft/stats");

     public static final NamespacedKey RARITY_KEY = new NamespacedKey(MainCore.instance, "rarity");
     public static final NamespacedKey MIN_DAMAGE_KEY = new NamespacedKey(MainCore.instance, "min_damage");
     public static final NamespacedKey MAX_DAMAGE_KEY = new NamespacedKey(MainCore.instance, "max_damage");
     public static final NamespacedKey CRITICAL_CHANCE_KEY = new NamespacedKey(MainCore.instance, "critical_chance");
     public static final NamespacedKey MIN_LEVEL_KEY = new NamespacedKey(MainCore.instance, "min_level");
     public static final NamespacedKey DEFENSE_KEY = new NamespacedKey(MainCore.instance, "defense");

     public static String textedUUID(Player p) {
          return p.getUniqueId().toString();
     }

     public static Rarity getRarityFromLevel(int level) {
          switch (level) {
               case 2:
                    return Rarity.RARE;
               case 3:
                    return Rarity.EPIC;
               case 4:
                    return Rarity.LEGENDARY;
               default:
                    return Rarity.COMMON;
          }
     }

     public static String getRoundedNumber(float number, int places) {
          return String.format("%."+places+"f", number);
     }

}
