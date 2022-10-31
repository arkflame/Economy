package dev._2lstudios.economy.entities;

import com.dotphin.classserializer.annotations.Serializable;
import com.dotphin.milkshake.Entity;

@Serializable
public class AccountEntity extends Entity {
    public String username;
    public String uuid;
    public double balance;
}
