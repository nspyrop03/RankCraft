package me.amc.rankcraft.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomItem {

     private ItemStack item;
     private Material type;
     private String name;
     private ItemMeta meta;
     private List<String> lore;
     private List<String> afterLore;
     private HashMap<Enchantment, Integer> enchantments;

     public CustomItem(Material type) {
          this.type = type;
          this.item = new ItemStack(type);
          this.meta = this.item.getItemMeta();
          this.name = this.meta.getDisplayName();
          this.lore = new ArrayList<>();
          this.afterLore = new ArrayList<>();
          this.enchantments = new HashMap<>();
     }

     public CustomItem(Material type, String name) {
          this(type);
          this.name = name.replace('&', '§').replace('_', ' ');
     }

     public CustomItem(ItemStack item) {
          this.item = item;
          this.type = item.getType();
          this.meta = item.getItemMeta();
          this.name = this.meta.getDisplayName();
          this.lore = this.meta.getLore();
          this.afterLore = new ArrayList<>();
          this.enchantments = new HashMap<>();
          for(Enchantment enc : this.meta.getEnchants().keySet())
               this.enchantments.put(enc, this.meta.getEnchants().get(enc));
     }

     public ItemStack getItem() {
          return item;
     }

     public void setItem(ItemStack item) {
          this.item = item;
     }

     public Material getType() {
          return type;
     }

     public void setType(Material type) {
          this.type = type;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name.replace('&', '§').replace('_', ' ');
     }

     public ItemMeta getMeta() {
          return meta;
     }

     public void setMeta(ItemMeta meta) {
          this.meta = meta;
     }

     public List<String> getLore() {
          return lore;
     }

     public void setLore(List<String> lore) {
          this.lore = lore;
     }

     public void addLore(String... lines) {
          for(String s : lines)
               this.lore.add(s);
     }

     public List<String> getAfterLore() {
          return afterLore;
     }

     public void setAfterLore(List<String> afterLore) {
          this.afterLore = afterLore;
     }

     public void addAfterLore(String... lines) {
          for(String s : lines)
               this.afterLore.add(s);
     }

     public HashMap<Enchantment, Integer> getEnchantments() {
          return enchantments;
     }

     public void setEnchantments(HashMap<Enchantment, Integer> enchantments) {
          this.enchantments = enchantments;
     }

     public void addEnchantment(Enchantment enchantment, int level) {
          this.enchantments.put(enchantment, level);
     }

     public void reloadLore() {
          List<String> fullLore = new ArrayList<>();
          fullLore.addAll(this.lore);
          fullLore.addAll(this.afterLore);
          this.meta.setLore(fullLore);
     }

     public CustomItem buildSimpleItem(boolean isWithEnchantColor) {
          this.meta.setDisplayName(this.name);
          List<String> fullLore = new ArrayList<>();
          fullLore.addAll(this.lore);
          fullLore.addAll(this.afterLore);
          this.meta.setLore(fullLore);

          if (isWithEnchantColor) {
               this.meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
               this.meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
          }

          this.item.setItemMeta(this.meta);
          return this;
     }

     public CustomItem build() {
          this.meta.setDisplayName(this.name);

          reloadLore();

          for(Enchantment key : enchantments.keySet())
               this.meta.addEnchant(key, enchantments.get(key), true);

          this.item.setItemMeta(this.meta);
          return this;
     }

     public CustomItem buildWithNoFlags() {
          this.build();
          this.meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
          this.item.setItemMeta(this.meta);
          return this;
     }

}
