package me.amc.rankcraft.commands;

import me.amc.rankcraft.MainCore;
import me.amc.rankcraft.PermissionList;
import me.amc.rankcraft.RankCraft;

public class CommandsList {

     private RankCraft rc;
     private PermissionList perms;

     public SyncCommand syncCommand;
     public GiveCommand giveCommand;

     public CommandsList() {
          rc = MainCore.instance.rankCraft;
          perms = MainCore.instance.permList;

          initCommands();
          initAliases();
          makeDescriptions();
     }

     private void initCommands() {
          syncCommand = new SyncCommand();
          rc.registerSubCommand(syncCommand);

          giveCommand = new GiveCommand();
          rc.registerSubCommand(giveCommand);
     }

     private void initAliases() {}

     private void makeDescriptions() {
          rc.addCommandDescription("sync local", "Copy cloud data to local files", perms.sync_permission, true);
          rc.addCommandDescription("sync cloud", "Copy local data to cloud", perms.sync_permission, true);
     }

}
