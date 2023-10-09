package me.amc.rankcraft.events;

import me.amc.rankcraft.MainCore;
import me.amc.rankcraft.stats.PlayerStats;
import me.amc.rankcraft.stats.SaveSystem;
import me.amc.rankcraft.utils.RCUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockStatsEvents implements Listener {

     private SaveSystem saveSystem;
     private HashMap<String, ArrayList<String>> savedBlocks;

     public BlockStatsEvents() {
          Bukkit.getServer().getPluginManager().registerEvents(this, MainCore.instance);
          saveSystem = MainCore.instance.rankCraft.saveSystem;
          savedBlocks = new HashMap<>();
     }

     @EventHandler
     public void onBlockPlace(BlockPlaceEvent event) {
          Player p = event.getPlayer();
          Block b = event.getBlock();
          if(!isInSavedBlocks(p, b)) {
               PlayerStats ps = saveSystem.getPlayerStats(RCUtils.textedUUID(p));
               ps.setBlocksPlaced(ps.getBlocksPlaced() + 1);
               ps.addBlockExp(true, event.getBlock().getType());
               saveSystem.setPlayerStats(RCUtils.textedUUID(p), ps);
               addToSavedBlocks(p, b);
          }
     }

     @EventHandler
     public void onBlockBreak(BlockBreakEvent event) {
          Player p = event.getPlayer();
          Block b = event.getBlock();
          if(!isInSavedBlocks(p, b)) {
               String uuid = RCUtils.textedUUID(p);
               PlayerStats ps = saveSystem.getPlayerStats(uuid);
               ps.setBlocksBroken(ps.getBlocksBroken() + 1);
               ps.addBlockExp(false, event.getBlock().getType());
               saveSystem.setPlayerStats(uuid, ps);
          }
     }

     private void addToSavedBlocks(Player p, Block b) {
          if(MainCore.instance.config.saveBlocks) {
               Location loc = b.getLocation();
               String s = ""+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ();
               String uuid = p.getUniqueId().toString();
               if(savedBlocks.containsKey(uuid)) {
                    if(!isInSavedBlocks(p, b)) {
                         savedBlocks.get(uuid).add(s);
                         if(savedBlocks.get(uuid).size() > MainCore.instance.config.blocksToSave) {
                              savedBlocks.get(uuid).remove(0);
                         }
                    }
               } else {
                    savedBlocks.put(uuid, new ArrayList<>());
                    savedBlocks.get(uuid).add(s);
               }
          }
     }

     private boolean isInSavedBlocks(Player p, Block b) {
          boolean found = false;
          if(MainCore.instance.config.saveBlocks) {
               String uuid = p.getUniqueId().toString();
               Location loc = b.getLocation();
               if(savedBlocks.containsKey(uuid)) {
                    for(String s : savedBlocks.get(uuid)) {
                         String[] parts = s.split(",");
                         int x = Integer.parseInt(parts[0]);
                         int y = Integer.parseInt(parts[1]);
                         int z = Integer.parseInt(parts[2]);
                         if(loc.getBlockX() == x && loc.getBlockY() == y && loc.getBlockZ() == z) {
                              found = true;
                         }
                    }
               }
          }
          return found;
     }
}
