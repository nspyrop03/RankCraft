package me.amc.rankcraft;

import me.amc.rankcraft.events.PlayerJoinQuitEvents;
import me.amc.rankcraft.stats.SaveSystem;

public class RankCraft {

     public SaveSystem saveSystem;

     public RankCraft() {
          saveSystem = new SaveSystem();
     }

     public void registerEvents() {
          new PlayerJoinQuitEvents();
     }

}
