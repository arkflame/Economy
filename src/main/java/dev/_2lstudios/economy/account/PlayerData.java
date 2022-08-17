package dev._2lstudios.economy.account;

import com.dotphin.classserializer.annotations.Serializable;
import com.dotphin.milkshake.Entity;

@Serializable
public class PlayerData extends Entity {
    public String username;
    public String uuid;
    public double balance;
}
