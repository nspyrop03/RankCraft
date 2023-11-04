package me.amc.rankcraft.rpgitem;

import me.amc.rankcraft.utils.RCUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class RpgItem extends RpgMaterial {

     private float minDamage;
     private float maxDamage;
     private int criticalChance;
     private int minLevel;

     public RpgItem(Material type, String name, float minDamage, float maxDamage, int criticalChance, int minLevel, Rarity rarity) {
          super(type, rarity);
          this.minDamage = minDamage;
          this.maxDamage = maxDamage;
          this.criticalChance = criticalChance;
          this.minLevel = minLevel;
          this.setName(name.replace('&', '§').replace('_', ' '));
     }

     public RpgItem(Material type, String name) {
          this(type, name, 0, 0, 0, 0, Rarity.COMMON);
     }

     // Copy Constructor
     public RpgItem(RpgItem ri) {
          this(ri.getType(), ri.getName(), ri.getMinDamage(), ri.getMaxDamage(), ri.getCriticalChance(), ri.getMinLevel(), ri.getRarity());
     }

     public RpgItem(ItemStack item) {
          this(item.getType(), item.getType().name());
          if(item.hasItemMeta()) {
               ItemMeta meta = item.getItemMeta();
               this.setName(meta.getDisplayName());
               this.setLore(meta.getLore());
               PersistentDataContainer pdc = meta.getPersistentDataContainer();
               if(pdc.has(RCUtils.RARITY_KEY, PersistentDataType.INTEGER))
                    this.setRarity(RCUtils.getRarityFromLevel(pdc.get(RCUtils.RARITY_KEY, PersistentDataType.INTEGER)));
               if(pdc.has(RCUtils.MIN_LEVEL_KEY, PersistentDataType.INTEGER))
                    this.setMinLevel(pdc.get(RCUtils.MIN_LEVEL_KEY, PersistentDataType.INTEGER));
               if(pdc.has(RCUtils.CRITICAL_CHANCE_KEY, PersistentDataType.INTEGER))
                    this.setCriticalChance(pdc.get(RCUtils.CRITICAL_CHANCE_KEY, PersistentDataType.INTEGER));
               if(pdc.has(RCUtils.MIN_DAMAGE_KEY, PersistentDataType.FLOAT))
                    this.setMinDamage(pdc.get(RCUtils.MIN_DAMAGE_KEY, PersistentDataType.FLOAT));
               if(pdc.has(RCUtils.MAX_DAMAGE_KEY, PersistentDataType.FLOAT))
                    this.setMaxDamage(pdc.get(RCUtils.MAX_DAMAGE_KEY, PersistentDataType.FLOAT));
          }
     }

     public float getMinDamage() {
          return minDamage;
     }

     public void setMinDamage(float minDamage) {
          this.minDamage = minDamage;
     }

     public float getMaxDamage() {
          return maxDamage;
     }

     public void setMaxDamage(float maxDamage) {
          this.maxDamage = maxDamage;
     }

     public int getCriticalChance() {
          return criticalChance;
     }

     public void setCriticalChance(int criticalChance) {
          this.criticalChance = criticalChance;
     }

     public int getMinLevel() {
          return minLevel;
     }

     public void setMinLevel(int minLevel) {
          this.minLevel = minLevel;
     }

     @Override
     public RpgItem build() {
          super.build();

          this.addLore("MinDamage: "+ RCUtils.getRoundedNumber(minDamage, 2),
                  "MaxDamage: "+RCUtils.getRoundedNumber(maxDamage, 2),
                  "CriticalChance: "+criticalChance,
                  "MinLevel: "+minLevel);

          this.reloadLore();

          this.getMeta().getPersistentDataContainer().set(RCUtils.MIN_DAMAGE_KEY, PersistentDataType.FLOAT, minDamage);
          this.getMeta().getPersistentDataContainer().set(RCUtils.MAX_DAMAGE_KEY, PersistentDataType.FLOAT, maxDamage);
          this.getMeta().getPersistentDataContainer().set(RCUtils.CRITICAL_CHANCE_KEY, PersistentDataType.INTEGER, criticalChance);
          this.getMeta().getPersistentDataContainer().set(RCUtils.MIN_LEVEL_KEY, PersistentDataType.INTEGER, minLevel);

          this.getItem().setItemMeta(this.getMeta());

          return this;
     }

}
