package dev._2lstudios.economy.players;

import org.bukkit.entity.Player;

import dev._2lstudios.economy.account.PlayerDataManager;
import dev._2lstudios.economy.plugins.EconomyPlugin;

public class OfflinePlayer extends EconomyPlayer {
    private String username;

    public OfflinePlayer(EconomyPlugin plugin, Player bukkitPlayer, String username) {
        super(plugin, bukkitPlayer);
        this.username = username;
    }

    @Override
    public String getLowerName() {
        return this.username.toLowerCase();
    }

    @Override
    public String getName() {
        return this.username;
    }

    @Override
    public void download() {
        PlayerDataManager dataManager = this.getPlugin().getPlayerDataManager();
        this.setData(dataManager.getByUsername(this.getLowerName()));
    }
}