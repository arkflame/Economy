package dev._2lstudios.economy.entities;

import com.dotphin.classserializer.annotations.Serializable;
import com.dotphin.milkshake.Entity;

import dev._2lstudios.economy.Account;

@Serializable
public class AccountEntity extends Entity {
    public String username;
    public String uuid;
    public double balance;

    public AccountEntity(String username, String uuid, double balance) {
        this.username = username;
        this.uuid = uuid;
        this.balance = balance;
    }

    public AccountEntity(String id, String username, String uuid, double balance) {
        this(username, uuid, balance);
        setID(id);
    }

    public Account toAccount() {
        return new Account(getID(), username, uuid, balance);
    }
}
