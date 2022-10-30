package dev._2lstudios.economy.providers;

import java.util.List;

import org.bukkit.OfflinePlayer;

import dev._2lstudios.economy.api.EconomyAPI;
import dev._2lstudios.economy.config.Configuration;
import dev._2lstudios.economy.errors.AccountNotFoundException;
import dev._2lstudios.economy.errors.MaxBalanceLimitReachedException;
import dev._2lstudios.economy.errors.MinBalanceLimitReachedException;
import dev._2lstudios.economy.plugins.EconomyPlugin;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class EconomyProvider implements Economy {
    private EconomyAPI api;
    private Configuration config;

    public EconomyProvider(EconomyPlugin plugin) {
        this.api = plugin.getAPI();
        this.config = plugin.getConfig();
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean createPlayerAccount(String player) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return this.api.createPlayerAccount(player);
    }

    @Override
    public boolean createPlayerAccount(String player, String world) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String world) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String currencyNamePlural() {
        return this.config.getString("economy.currency.plural");
    }

    @Override
    public String currencyNameSingular() {
        return this.config.getString("economy.currency.singular");
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String player, double amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        try {
            double newBalance = this.api.give(player, amount);
            return new EconomyResponse(amount, newBalance, ResponseType.SUCCESS, null);
        } catch (MaxBalanceLimitReachedException | AccountNotFoundException e) {
            return new EconomyResponse(amount, 0, ResponseType.FAILURE, e.getMessage());
        }
    }

    @Override
    public EconomyResponse depositPlayer(String arg0, String arg1, double arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String format(double amount) {
        return String.valueOf(amount);
    }

    @Override
    public int fractionalDigits() {
        return this.config.getInt("economy.currency.fractionals");
    }

    @Override
    public double getBalance(String arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        try {
            return this.api.getBalance(player);
        } catch (AccountNotFoundException e) {
            return 0;
        }
    }

    @Override
    public double getBalance(String arg0, String arg1) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer arg0, String arg1) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<String> getBanks() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        return "2LSEconomy";
    }

    @Override
    public boolean has(String arg0, double arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        try {
            return this.api.getBalance(player) >= amount;
        } catch (AccountNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean has(String arg0, String arg1, double arg2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasAccount(String arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return this.api.getPlayerData(player) != null;
    }

    @Override
    public boolean hasAccount(String arg0, String arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer arg0, String arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasBankSupport() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public EconomyResponse isBankMember(String arg0, String arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String arg0, String arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public EconomyResponse withdrawPlayer(String arg0, double arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        try {
            double newBalance = this.api.take(player, amount);
            return new EconomyResponse(amount, newBalance, ResponseType.SUCCESS, null);
        } catch (MinBalanceLimitReachedException | AccountNotFoundException e) {
            return new EconomyResponse(amount, 0, ResponseType.FAILURE, e.getMessage());
        }
    }

    @Override
    public EconomyResponse withdrawPlayer(String arg0, String arg1, double arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
