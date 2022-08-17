package dev._2lstudios.economy.players;

import org.bukkit.entity.Player;

import dev._2lstudios.economy.EconomyPlugin;
import dev._2lstudios.economy.account.PlayerData;
import dev._2lstudios.economy.account.PlayerDataManager;
import dev._2lstudios.economy.errors.AccountNotFoundException;
import dev._2lstudios.economy.errors.MaxBalanceLimitReachedException;
import dev._2lstudios.economy.errors.MinBalanceLimitReachedException;

public class EconomyPlayer extends EconomyPlayerBase {
    private PlayerData data = null;
    private boolean downloaded = false;

    public EconomyPlayer(EconomyPlugin plugin, Player bukkitPlayer) {
        super(plugin, bukkitPlayer);
    }

    public PlayerData getData() {
        return this.data;
    }
    
    public void setData(PlayerData data) {
        this.data = data;
    }

    public void download() {
        Player player = this.getBukkitPlayer();
        PlayerDataManager dataManager = this.getPlugin().getPlayerDataManager();
        this.data = dataManager.getByPlayer(player);
        this.downloaded = true;

        if (this.data == null) {
            this.createAccount();
        }
    }

    public boolean isDownloaded() {
        return this.downloaded;
    }

    public boolean createAccount() {
        if (this.data == null) {
            PlayerData data = this.getPlugin().getPlayerDataManager().createAccount(
                this.getBukkitPlayer()
            );
            this.data = data;
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() {
        if (this.data != null) {
            return this.data.balance;
        } else {
            return 0;
        }
    }

    public double give(double amount) throws MaxBalanceLimitReachedException {
        try {
            return this.getPlugin().getAPI().give(this, amount);
        } catch (AccountNotFoundException e) {
            return 0;
        }
    }

    public double take(double amount) throws MinBalanceLimitReachedException {
        try {
            return this.getPlugin().getAPI().take(this, amount);
        } catch (AccountNotFoundException e) {
            return 0;
        }
    }
}