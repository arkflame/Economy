package dev._2lstudios.economy;

import dev._2lstudios.economy.entities.AccountEntity;
import dev._2lstudios.economy.plugins.EconomyPlugin;

public class Account {
    private String id;
    private String username;
    private String uuid;
    private double balance;

    public Account(String id, String username, String uuid, double balance) {
        this.username = username;
        this.uuid = uuid;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUuid() {
        return uuid;
    }

    public double getBalance() {
        return balance;
    }

    public Account fetch() {
        AccountEntity accountEntity = EconomyPlugin.getAPI().getPlugin().getAccountRepository().findByID(id);

        username = accountEntity.username;
        uuid = accountEntity.uuid;
        balance = accountEntity.balance;

        return this;
    }

    public AccountEntity toAccountEntity() {
        return new AccountEntity(id, username, uuid, balance);
    }
}
