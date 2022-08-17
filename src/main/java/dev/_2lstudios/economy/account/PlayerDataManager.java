package dev._2lstudios.economy.account;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.dotphin.milkshake.Milkshake;
import com.dotphin.milkshake.Provider;
import com.dotphin.milkshake.Repository;
import com.dotphin.milkshake.find.FindFilter;

public class PlayerDataManager {
    private Map<String, PlayerData> cache;
    private Repository<PlayerData> repository;

    public PlayerDataManager(Provider provider, String collectionName) {
        this.cache = new HashMap<>();
        this.repository = Milkshake.addRepository(PlayerData.class, provider, collectionName);
    }
    
    public PlayerData storeInCache(PlayerData account) {
        if (account != null) {
            this.cache.put(account.getID(), account);
        }
        return account;
    }

    public PlayerData createAccount(String username, String uuid, double initialBalance) {
        PlayerData acc = new PlayerData();
        acc.username = username;
        acc.uuid = uuid;
        acc.balance = initialBalance;
        acc.save();

        Player player = Bukkit.getPlayer(username);
        boolean isOnline = player != null && player.isOnline();
        
        return isOnline ? this.storeInCache(acc) : acc;
    }

    public PlayerData createAccount(String username, String uuid) {
        return this.createAccount(username, uuid, 0);
    }

    public PlayerData createAccount(OfflinePlayer player, double initialBalance) {
        return this.createAccount(player.getName().toLowerCase(), player.getUniqueId().toString(), initialBalance);
    }

    public PlayerData createAccount(OfflinePlayer player) {
        return this.createAccount(player.getName().toLowerCase(), player.getUniqueId().toString());
    }

    public PlayerData getCachedAccount(String id) {
        return this.cache.get(id);
    }

    public PlayerData removeCachedAccount(String id) {
        return this.cache.remove(id);
    }

    public PlayerData removeCachedAccount(PlayerData acc) {
        return this.removeCachedAccount(acc.getID());
    }

    public PlayerData getByUsername(String username) {
        PlayerData acc = this.repository.findOne(new FindFilter("username", username));
        return this.storeInCache(acc);
    }

    public PlayerData getByUUID(String uuid) {
        PlayerData acc = this.repository.findOne(new FindFilter("uuid", uuid));
        return this.storeInCache(acc);
    }

    public PlayerData getByUUID(UUID uuid) {
        return this.getByUUID(uuid.toString());
    }
    
    public PlayerData getByPlayer(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();
        String username = player.getName().toLowerCase();
        boolean requireFix = false;

        PlayerData acc = this.getByUUID(uuid);

        if (acc == null) {
            acc = this.getByUsername(username);
        }

        if (acc == null) {
            return null;
        }

        if (acc.username != username) {
            acc.username = username;
            requireFix = true;
        } else if (acc.uuid != uuid) {
            acc.uuid = uuid;
            requireFix = true;
        }

        if (requireFix) {
            acc.save();
        }

        return this.storeInCache(acc);
    }
}
