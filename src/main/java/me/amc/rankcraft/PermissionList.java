package me.amc.rankcraft;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class PermissionList {

     private List<Permission> permissions;

     public Permission sync_permission;

     public PermissionList() {
          init();
          for(Permission perm : permissions)
               Bukkit.getServer().getPluginManager().addPermission(perm);
     }

     private void init() {
          permissions = new ArrayList<>();

          sync_permission = new Permission("rankcraft.sync");
          permissions.add(sync_permission);
     }
}
