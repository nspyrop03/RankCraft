package me.amc.rankcraft.rpgitem;

import me.amc.rankcraft.utils.CustomItem;
import me.amc.rankcraft.utils.RCUtils;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataType;

public class RpgMaterial extends CustomItem {

     private Rarity rarity;
     private int customModelId; // -1 used for no id

     public RpgMaterial(Material type, Rarity rarity, int customModelId) {
          super(type);
          this.rarity = rarity;
          this.customModelId = customModelId;
          this.addAfterLore("Rarity: "+rarity); // change this!
     }

     public RpgMaterial(Material type, Rarity rarity) {
          this(type, rarity, -1);
     }

     public RpgMaterial(Material type, String name) {
          this(type, Rarity.COMMON, -1);
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

     @Override
     public RpgMaterial build() {
          super.build();

          if(this.customModelId > 0)
               this.getMeta().setCustomModelData(this.customModelId);
          this.getMeta().getPersistentDataContainer().set(RCUtils.RARITY_KEY, PersistentDataType.INTEGER, this.rarity.getLevel());

          this.getItem().setItemMeta(this.getMeta());

          return this;
     }
}
