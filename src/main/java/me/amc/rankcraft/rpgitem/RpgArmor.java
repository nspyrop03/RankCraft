package me.amc.rankcraft.rpgitem;

import me.amc.rankcraft.utils.RCUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class RpgArmor extends RpgMaterial {

     private float defense;

     // TO-DO: add check if material is armor

     public RpgArmor(Material type, String name, float defense, int minLevel, Rarity rarity) {
          super(type, minLevel, rarity);
          this.defense = defense;
          this.setName(name.replace('&', '§').replace('_', ' '));
     }

     public RpgArmor(Material type, String name) {
          this(type, name, 0, 0, Rarity.COMMON);
     }

     // Copy Constructor
     public RpgArmor(RpgArmor ri) {
          this(ri.getType(), ri.getName(), ri.getDefense(), ri.getMinLevel(), ri.getRarity());
     }

     public RpgArmor(ItemStack item) {
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
               if(pdc.has(RCUtils.DEFENSE_KEY, PersistentDataType.FLOAT))
                    this.setDefense(pdc.get(RCUtils.DEFENSE_KEY, PersistentDataType.FLOAT));

          }
     }

     public float getDefense() {
          return defense;
     }

     public void setDefense(float defense) {
          this.defense = defense;
     }

     @Override
     public RpgArmor build() {
          super.build();

          this.addLore("Defense: "+ RCUtils.getRoundedNumber(defense, 2));

          this.reloadLore();

          this.getMeta().getPersistentDataContainer().set(RCUtils.DEFENSE_KEY, PersistentDataType.FLOAT, defense);

          this.getItem().setItemMeta(this.getMeta());

          return this;
     }
}
