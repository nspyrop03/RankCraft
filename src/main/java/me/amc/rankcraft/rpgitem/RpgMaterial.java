package me.amc.rankcraft.rpgitem;

import me.amc.rankcraft.utils.CustomItem;
import me.amc.rankcraft.utils.RCUtils;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataType;

public class RpgMaterial extends CustomItem {

     private Rarity rarity;
     private int minLevel;
     private int customModelId; // -1 used for no id

     public RpgMaterial(Material type, Rarity rarity, int minLevel, int customModelId) {
          super(type);
          this.rarity = rarity;
          this.customModelId = customModelId;
          this.minLevel = minLevel;
          this.addAfterLore("MinLevel: "+minLevel, "Rarity: "+rarity); // change this!
     }

     public RpgMaterial(Material type, int minLevel, Rarity rarity) {
          this(type, rarity, minLevel,-1);
     }

     public RpgMaterial(Material type, String name) {
          this(type, Rarity.COMMON, 0, -1);
          this.setName(name.replace('&', '§').replace('_', ' '));
     }

     public Rarity getRarity() {
          return rarity;
     }

     public void setRarity(Rarity rarity) {
          this.rarity = rarity;
     }

     public int getCustomModelId() {
          return customModelId;
     }

     public void setCustomModelId(int customModelId) {
          this.customModelId = customModelId;
     }

     public int getMinLevel() {
          return this.minLevel;
     }

     public void setMinLevel(int minLevel) {
          this.minLevel = minLevel;
     }

     @Override
     public RpgMaterial build() {
          super.build();

          if(this.customModelId > 0)
               this.getMeta().setCustomModelData(this.customModelId);
          this.getMeta().getPersistentDataContainer().set(RCUtils.RARITY_KEY, PersistentDataType.INTEGER, this.rarity.getLevel());
          this.getMeta().getPersistentDataContainer().set(RCUtils.MIN_LEVEL_KEY, PersistentDataType.INTEGER, minLevel);

          this.getItem().setItemMeta(this.getMeta());

          return this;
     }
}
