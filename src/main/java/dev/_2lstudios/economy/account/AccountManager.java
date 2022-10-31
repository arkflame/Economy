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

import dev._2lstudios.economy.entities.AccountEntity;

public class AccountManager {
    private Map<String, AccountEntity> cache;
    private Repository<AccountEntity> repository;

    public AccountManager(Provider provider, String collectionName) {
        this.cache = new HashMap<>();
        this.repository = Milkshake.addRepository(AccountEntity.class, provider, collectionName);
    }

    public AccountEntity storeInCache(AccountEntity account) {
        if (account != null) {
            this.cache.put(account.getID(), account);
        }
        return account;
    }

    public AccountEntity createAccount(String username, String uuid, double initialBalance) {
        AccountEntity acc = new AccountEntity(username, uuid, initialBalance);

        acc.save();

        Player player = Bukkit.getPlayer(username);
        boolean isOnline = player != null && player.isOnline();

        return isOnline ? this.storeInCache(acc) : acc;
    }

    public AccountEntity createAccount(String username, String uuid) {
        return this.createAccount(username, uuid, 0);
    }

    public AccountEntity createAccount(OfflinePlayer player, double initialBalance) {
        return this.createAccount(player.getName().toLowerCase(), player.getUniqueId().toString(), initialBalance);
    }

    public AccountEntity createAccount(OfflinePlayer player) {
        return this.createAccount(player.getName().toLowerCase(), player.getUniqueId().toString());
    }

    public AccountEntity getCachedAccount(String id) {
        return this.cache.get(id);
    }

    public AccountEntity removeCachedAccount(String id) {
        return this.cache.remove(id);
    }

    public AccountEntity removeCachedAccount(AccountEntity acc) {
        return this.removeCachedAccount(acc.getID());
    }

    public AccountEntity getByUsername(String username) {
        AccountEntity acc = this.repository.findOne(new FindFilter("username", username));
        return this.storeInCache(acc);
    }

    public AccountEntity getByUUID(String uuid) {
        AccountEntity acc = this.repository.findOne(new FindFilter("uuid", uuid));
        return this.storeInCache(acc);
    }

    public AccountEntity getByUUID(UUID uuid) {
        return this.getByUUID(uuid.toString());
    }

    public AccountEntity getByPlayer(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();
        String username = player.getName().toLowerCase();
        boolean requireFix = false;

        AccountEntity acc = this.getByUUID(uuid);

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
