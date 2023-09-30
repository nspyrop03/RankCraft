package me.amc.rankcraft.events;

import me.amc.rankcraft.MainCore;
import me.amc.rankcraft.RankCraft;
import me.amc.rankcraft.stats.PlayerStats;
import me.amc.rankcraft.utils.RCUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class PlayerJoinQuitEvents implements Listener {

     private RankCraft rc;

     public PlayerJoinQuitEvents() {
          rc = MainCore.instance.rankCraft;
          Bukkit.getServer().getPluginManager().registerEvents(this, MainCore.instance);
     }

     @EventHandler
     public void onJoin(PlayerJoinEvent event) {
          Player p = event.getPlayer();

          rc.saveSystem.initForPlayer(p);
          rc.saveSystem.save();

          testOnlineDatabase(p);
     }

     @EventHandler
     public void onQuit(PlayerQuitEvent event) {

     }

     private void testOnlineDatabase(Player p) {
          if(MainCore.instance.config.enableDb) {
               try {
                    PlayerStats ps = MainCore.instance.statsDatabase.findPlayerStatsByUUID(RCUtils.textedUUID(p));
                    p.sendMessage(ps.toString());
               } catch (SQLException e) {
                    e.printStackTrace();
               }
          }
     }

}
