package dev._2lstudios.economy.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import dev._2lstudios.economy.EconomyPlugin;

public class PlayerQuitListener implements Listener {
  private EconomyPlugin plugin;

  public PlayerQuitListener(EconomyPlugin plugin) {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent e) {
    this.plugin.getPlayerManager().removePlayer(e.getPlayer());
  }
}
