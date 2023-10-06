package me.amc.rankcraft;

import me.amc.rankcraft.commands.CommandDescription;
import me.amc.rankcraft.commands.CommandsList;
import me.amc.rankcraft.commands.SubCommand;
import me.amc.rankcraft.events.BlockStatsEvents;
import me.amc.rankcraft.events.PlayerJoinQuitEvents;
import me.amc.rankcraft.stats.SaveSystem;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RankCraft {

     public SaveSystem saveSystem;
     public CommandsList commandsList;
     public List<CommandDescription> commandDescriptions = new ArrayList<>();

     private HashMap<String, SubCommand> commands = new HashMap<>();

     public RankCraft() {
          saveSystem = new SaveSystem();
     }

     public void registerEvents() {
          new PlayerJoinQuitEvents();
          new BlockStatsEvents();
     }

     public void registerSubCommand(SubCommand command) {
          commands.put(command.getLabel(), command);
     }

     public SubCommand getSubCommandFromLabel(String label) {
          return commands.get(label);
     }

     public HashMap<String, SubCommand> getSubCommandHM() {
          return commands;
     }

     public void initSubCommands() {
          commandsList = new CommandsList();
     }

     public void addCommandDescription(String c, String d, Permission p, boolean l) {
          commandDescriptions.add(new CommandDescription(c, d, p, l));
     }
}
