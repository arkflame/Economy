package dev._2lstudios.economy.api;

import org.bukkit.entity.Player;

import dev._2lstudios.economy.Economy;
import dev._2lstudios.economy.players.EconomyPlayer;

public class EconomyAPI {
    private static Economy plugin;
    
    public EconomyAPI(Economy plugin) {
        EconomyAPI.plugin = plugin;
    }

    public static EconomyPlayer getPlayer(Player player) {
        return plugin.getPlayerManager().getPlayer(player);
    }
}
