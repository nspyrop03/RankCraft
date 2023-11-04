package me.amc.rankcraft.commands;

import me.amc.rankcraft.rpgitem.Rarity;
import me.amc.rankcraft.rpgitem.RpgItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class GiveCommand extends SubCommand {

     public GiveCommand() {
          super("give");
     }

     @Override
     public void execute(Player p, String[] args) {

          if(args.length == 2) {
               if(args[1].equalsIgnoreCase("test")) { // rc give test
                    RpgItem rpgItem = new RpgItem(Material.IRON_SWORD, ChatColor.GOLD+"First RpgItem",
                            5.0f, 10.0f, 45, 1, Rarity.RARE);
                    p.getInventory().addItem(rpgItem.build().getItem());
               }
          }

     }

     @Override
     public List<String> getTabCompletion(Player p, String[] args) {
          return null;
     }
}
