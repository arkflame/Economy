package dev._2lstudios.economy.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev._2lstudios.economy.players.EconomyPlayer;
import dev._2lstudios.economy.plugins.EconomyPlugin;

public class PlayerJoinListener implements Listener {
  private EconomyPlugin plugin;

  public PlayerJoinListener(EconomyPlugin plugin) {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    EconomyPlayer player = this.plugin.getPlayerManager().addPlayer(e.getPlayer());
    player.download();
  }
}
