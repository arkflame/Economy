package dev._2lstudios.economy.api;

import java.util.UUID;

import org.bukkit.OfflinePlayer;

import dev._2lstudios.economy.account.AccountManager;
import dev._2lstudios.economy.entities.AccountEntity;
import dev._2lstudios.economy.errors.AccountNotFoundException;
import dev._2lstudios.economy.errors.MaxBalanceLimitReachedException;
import dev._2lstudios.economy.errors.MinBalanceLimitReachedException;
import dev._2lstudios.economy.players.EconomyPlayer;
import dev._2lstudios.economy.players.EconomyPlayerManager;
import dev._2lstudios.economy.plugins.EconomyPlugin;
import dev._2lstudios.economy.pubsub.packets.BalanceSyncPacket;

public class EconomyAPI {
    /* Static Instance */
    private static EconomyAPI instance;

    public static EconomyAPI getAPI() {
        return instance;
    }

    /* API */
    private EconomyPlugin plugin;
    private EconomyPlayerManager playerManager;
    private AccountManager playerDataManager;
    
    public EconomyAPI(EconomyPlugin plugin) {
        this.plugin = plugin;
        this.playerManager = plugin.getPlayerManager();
        this.playerDataManager = plugin.getPlayerDataManager();

        EconomyAPI.instance = this;
    }

    // CRUD operations.
    public EconomyPlayer getPlayer(String playerName) {
        return playerManager.getPlayer(playerName.toLowerCase());
    }

    public EconomyPlayer getPlayer(UUID uuid) {
        return this.getPlayer(uuid);
    }

    public EconomyPlayer getPlayer(OfflinePlayer player) {
        return this.getPlayer(player.getName());
    }

    public AccountEntity getPlayerData(String playerName) {
        EconomyPlayer ecoplayer = this.getPlayer(playerName);
        return ecoplayer != null ? ecoplayer.getData() : playerDataManager.getByUsername(playerName);
    }

    public AccountEntity getPlayerData(UUID uuid) {
        EconomyPlayer ecoplayer = this.getPlayer(uuid);
        return ecoplayer != null ? ecoplayer.getData() : playerDataManager.getByUUID(uuid);
    }

    public AccountEntity getPlayerData(OfflinePlayer player) {
        EconomyPlayer ecoplayer = this.getPlayer(player);
        return ecoplayer != null ? ecoplayer.getData() : playerDataManager.getByPlayer(player);
    }

    public boolean createPlayerAccount(OfflinePlayer player) {
        if (this.getPlayerData(player) == null) {
            EconomyPlayer ecoplayer = this.getPlayer(player);
            AccountEntity data = playerDataManager.createAccount(
                player,
                this.plugin.getConfig().getDouble("economy.balance.initial")
            ); 

            if (ecoplayer != null) {
                ecoplayer.setData(data);
            }

            return true;
        } else {
            return false;
        }
    }

    // Sync operations
    public void syncBalance(String username, String accountId, double oldBalance, double newBalance) {
        BalanceSyncPacket packet = new BalanceSyncPacket(username, accountId, oldBalance, newBalance);
        this.plugin.getPubSub().send(packet);
    }

    // Economy operations
    public double getBalance(AccountEntity data) throws AccountNotFoundException {
        if (data != null) {
            return data.balance;
        } else {
            throw new AccountNotFoundException();
        }
    }

    public double getBalance(String playerName) throws AccountNotFoundException {
        return this.getBalance(this.getPlayerData(playerName));
    }

    public double getBalance(UUID uuid) throws AccountNotFoundException {
        return this.getBalance(this.getPlayerData(uuid));
    }

    public double getBalance(OfflinePlayer player) throws AccountNotFoundException {
        return this.getBalance(this.getPlayerData(player));
    }

    public double give(AccountEntity account, double amount) throws MaxBalanceLimitReachedException, AccountNotFoundException {
        if (account != null) {
            double max = this.plugin.getConfig().getDouble("economy.balance.max");
            double oldBalance = account.balance;
            double newBalance = oldBalance + amount;

            if (newBalance > max) {
                throw new MaxBalanceLimitReachedException(max);
            } else {
                account.balance = newBalance;
                account.save();
                this.syncBalance(account.username, account.getID(), oldBalance, newBalance);
                return newBalance;
            }
        } else {
           throw new AccountNotFoundException();
        }
    }

    public double give(EconomyPlayer player, double amount) throws MaxBalanceLimitReachedException, AccountNotFoundException {
        return this.give(player.getData(), amount);
    }

    public double give(OfflinePlayer player, double amount) throws MaxBalanceLimitReachedException, AccountNotFoundException {
        return this.give(this.getPlayerData(player), amount);
    }

    public double give(UUID uuid, double amount) throws MaxBalanceLimitReachedException, AccountNotFoundException {
        return this.give(this.getPlayerData(uuid), amount);
    }

    public double give(String username, double amount) throws MaxBalanceLimitReachedException, AccountNotFoundException {
        return this.give(this.getPlayerData(username), amount);
    }

    public double take(AccountEntity account, double amount) throws MinBalanceLimitReachedException, AccountNotFoundException {
        if (account != null) {
            double min = this.plugin.getConfig().getDouble("economy.balance.min");
            double oldBalance = account.balance;
            double newBalance = oldBalance - amount;

            if (newBalance < min) {
                throw new MinBalanceLimitReachedException(min);
            } else {
                account.balance = newBalance;
                account.save();
                this.syncBalance(account.username, account.getID(), oldBalance, newBalance);
                return newBalance;
            }
        } else {
            throw new AccountNotFoundException();
        }
    }

    public double take(EconomyPlayer player, double amount) throws MinBalanceLimitReachedException, AccountNotFoundException {
        return this.take(player.getData(), amount);
    }

    public double take(OfflinePlayer player, double amount) throws MinBalanceLimitReachedException, AccountNotFoundException {
        return this.take(this.getPlayerData(player), amount);
    }

    public double take(UUID uuid, double amount) throws MinBalanceLimitReachedException, AccountNotFoundException {
        return this.take(this.getPlayerData(uuid), amount);
    }

    public double take(String username, double amount) throws MinBalanceLimitReachedException, AccountNotFoundException {
        return this.take(this.getPlayerData(username), amount);
    }
}
