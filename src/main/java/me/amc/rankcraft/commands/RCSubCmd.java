package me.amc.rankcraft.commands;

import org.bukkit.entity.Player;

import java.util.List;

public interface RCSubCmd {

     void execute(Player p, String[] args);

     List<String> getTabCompletion(Player p, String[] args);

}
