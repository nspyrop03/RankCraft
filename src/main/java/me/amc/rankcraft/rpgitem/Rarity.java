package me.amc.rankcraft.rpgitem;

import org.bukkit.ChatColor;

public enum Rarity {
     COMMON(ChatColor.GREEN, 1),
     RARE(ChatColor.BLUE, 2),
     EPIC(ChatColor.GOLD, 3),
     LEGENDARY(ChatColor.DARK_PURPLE, 4);

     private ChatColor color;
     private int level;

     Rarity(ChatColor color, int level) {
          this.color = color;
          this.level = level;
     }

     public ChatColor getColor() {
          return this.color;
     }

     public int getLevel() {
          return this.level;
     }
}
