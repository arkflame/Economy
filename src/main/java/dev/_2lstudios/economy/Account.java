package dev._2lstudios.economy;

import dev._2lstudios.economy.entities.AccountEntity;

public class Account {
    private String username;
    private String uuid;
    private double balance;

    public Account(String username, String uuid, double balance) {
        this.username = username;
        this.uuid = uuid;
        this.balance = balance;
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

    public AccountEntity toAccountEntity() {
        return new AccountEntity(username, uuid, balance);
    }
}
