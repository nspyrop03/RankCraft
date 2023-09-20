package me.amc.rankcraft.stats;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.Map;
import java.util.TreeMap;

@SerializableAs("CharacterStats")
public class CharacterStats implements ConfigurationSerializable {

     private float exp;
     private int level;
     private float gold;
     private int blocksPlaced;
     private int blocksBroken;
     private int mana;
     private int maxMana;

     public CharacterStats(float exp, int level,
                           float gold, int blocksPlaced,
                           int blocksBroken, int mana, int maxMana) {
          this.exp = exp;
          this.level = level;
          this.gold = gold;
          this.blocksPlaced = blocksPlaced;
          this.blocksBroken = blocksBroken;
          this.mana = mana;
          this.maxMana = maxMana;
     }

     public CharacterStats(Map<String, Object> map) {
          this.exp = (float) map.get("exp");
          this.level = (int) map.get("level");
          this.gold = (float) map.get("gold");
          this.blocksPlaced = (int) map.get("blocksPlaced");
          this.blocksBroken = (int) map.get("blocksBroken");
          this.mana = (int) map.get("mana");
          this.maxMana = (int) map.get("maxMana");
     }

     @Override
     public Map<String, Object> serialize() {
          Map<String, Object> map = new TreeMap<String, Object>();
          map.put("exp", this.exp);
          map.put("level", this.level);
          map.put("gold", this.gold);
          map.put("blocksPlaced", this.blocksPlaced);
          map.put("blocksBroken", this.blocksBroken);
          map.put("mana", this.mana);
          map.put("maxMana", this.maxMana);
          return map;
     }

     public float getExp() {
          return exp;
     }

     public int getLevel() {
          return level;
     }

     public float getGold() {
          return gold;
     }

     public int getBlocksPlaced() {
          return blocksPlaced;
     }

     public int getBlocksBroken() {
          return blocksBroken;
     }

     public int getMana() {
          return mana;
     }

     public int getMaxMana() {
          return maxMana;
     }

}
