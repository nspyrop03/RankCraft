package me.amc.rankcraft.stats;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.Map;
import java.util.TreeMap;

@SerializableAs("PlayerStats")
public class PlayerStats implements ConfigurationSerializable {

     private String uuid;
     private float exp;
     private int level;
     private float gold;
     private int blocksPlaced;
     private int blocksBroken;
     private int mana;
     private int maxMana;

     public PlayerStats(String uuid, float exp, int level,
                           float gold, int blocksPlaced,
                           int blocksBroken, int mana, int maxMana) {
          this.uuid = uuid;
          this.exp = exp;
          this.level = level;
          this.gold = gold;
          this.blocksPlaced = blocksPlaced;
          this.blocksBroken = blocksBroken;
          this.mana = mana;
          this.maxMana = maxMana;
     }

     public PlayerStats(Map<String, Object> map) {
          this.uuid = (String) map.get("uuid");
          this.exp = ((Double)map.get("exp")).floatValue();
          this.level = (int) map.get("level");
          this.gold = ((Double)map.get("gold")).floatValue();
          this.blocksPlaced = (int) map.get("blocksPlaced");
          this.blocksBroken = (int) map.get("blocksBroken");
          this.mana = (int) map.get("mana");
          this.maxMana = (int) map.get("maxMana");
     }

     @Override
     public Map<String, Object> serialize() {
          Map<String, Object> map = new TreeMap<String, Object>();
          map.put("uuid", this.uuid);
          map.put("exp", this.exp);
          map.put("level", this.level);
          map.put("gold", this.gold);
          map.put("blocksPlaced", this.blocksPlaced);
          map.put("blocksBroken", this.blocksBroken);
          map.put("mana", this.mana);
          map.put("maxMana", this.maxMana);
          return map;
     }

     public String getUUID() {
          return this.uuid;
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

     public void setExp(float exp) {
          this.exp = exp;
     }

     public void setLevel(int level) {
          this.level = level;
     }

     public void setGold(float gold) {
          this.gold = gold;
     }

     public void setBlocksPlaced(int blocksPlaced) {
          this.blocksPlaced = blocksPlaced;
     }

     public void setBlocksBroken(int blocksBroken) {
          this.blocksBroken = blocksBroken;
     }

     public void setMana(int mana) {
          this.mana = mana;
     }

     public void setMaxMana(int maxMana) {
          this.maxMana = maxMana;
     }

     public void addGold(float gold) {
          setGold(getGold() + gold);
     }

     public void addExp(float exp) {
          setExp(getExp() + exp);
     }

     public void addBlockExp(boolean placed, Material blockMat) {
          
     }

     @Override
     public String toString() {
          return "PlayerStats[uuid="+this.uuid+"]:" +
                  "\n- exp="+this.exp+
                  "\n- level="+this.level+
                  "\n- gold="+this.gold+
                  "\n- blocksPlaced="+this.blocksPlaced+
                  "\n- blocksBroken="+this.blocksBroken+
                  "\n- mana="+this.mana+
                  "\n- maxMana="+this.maxMana;
     }
}
