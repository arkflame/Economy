package dev._2lstudios.economy.players;

import org.bukkit.entity.Player;

import dev._2lstudios.economy.Economy;

public class OfflinePlayer extends EconomyPlayer {
    private String username;

    public OfflinePlayer(Economy plugin, Player bukkitPlayer, String username) {
        super(plugin, bukkitPlayer);
        this.username = username.toLowerCase();
    }

    @Override
    public String getLowerName() {
        return this.username;
    }
}