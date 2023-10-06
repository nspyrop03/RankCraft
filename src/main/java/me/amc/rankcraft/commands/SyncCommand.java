package me.amc.rankcraft.commands;

import com.sun.tools.javac.Main;
import me.amc.rankcraft.MainCore;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class SyncCommand extends SubCommand {

     public SyncCommand() {
          super("sync");
     }

     @Override
     public void execute(Player p, String[] args) {
          if(args.length == 2) {
               if(args[1].equalsIgnoreCase("cloud")) {
                    if(MainCore.instance.config.enableDb) {
                         try {
                              MainCore.instance.rankCraft.saveSystem.syncCloud();
                              p.sendMessage("Successfully synced cloud data!");
                         } catch (IOException | InvalidConfigurationException | SQLException e) {
                              p.sendMessage("An error occurred!!!");
                              MainCore.instance.getLogger().log(Level.SEVERE, "Could not sync cloud!", e);
                         }
                    } else {
                         // Database not enabled
                    }
               } else if(args[1].equalsIgnoreCase("local")) {
                    if(MainCore.instance.config.enableDb) {
                         try {
                              MainCore.instance.rankCraft.saveSystem.syncLocal();
                              p.sendMessage("Successfully synced local data!");
                         } catch (IOException |  SQLException e) {
                              p.sendMessage("An error occurred!!!");
                              MainCore.instance.getLogger().log(Level.SEVERE, "Could not sync local!", e);
                         }
                    } else {
                         // Database not enabled
                    }
               } else {
                    // Invalid command syntax
               }
          } else {
               // Invalid command syntax
          }
     }

     @Override
     public List<String> getTabCompletion(Player p, String[] args) {
          if(args.length == 2)
               return Arrays.asList("cloud", "local");
          return null;
     }

}
