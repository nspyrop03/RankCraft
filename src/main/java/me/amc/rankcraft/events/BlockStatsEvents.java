package me.amc.rankcraft.events;

import me.amc.rankcraft.MainCore;
import me.amc.rankcraft.stats.PlayerStats;
import me.amc.rankcraft.stats.SaveSystem;
import me.amc.rankcraft.utils.RCUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockStatsEvents implements Listener {

     private SaveSystem saveSystem;

     public BlockStatsEvents() {
          Bukkit.getServer().getPluginManager().registerEvents(this, MainCore.instance);
          saveSystem = MainCore.instance.rankCraft.saveSystem;
     }

     @EventHandler
     public void onBlockPlace(BlockPlaceEvent event) {
          Player p = event.getPlayer();
          PlayerStats ps = saveSystem.getPlayerStats(RCUtils.textedUUID(p));
          ps.setBlocksPlaced(ps.getBlocksPlaced() + 1);
          saveSystem.setPlayerStats(RCUtils.textedUUID(p), ps);
     }

     @EventHandler
     public void onBlockBreak(BlockBreakEvent event) {
          String uuid = RCUtils.textedUUID(event.getPlayer());
          PlayerStats ps = saveSystem.getPlayerStats(uuid);
          ps.setBlocksBroken(ps.getBlocksBroken() + 1);
          saveSystem.setPlayerStats(uuid, ps);
     }
}
