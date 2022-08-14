package dev._2lstudios.economy.commands;


import com.iridium.iridiumcolorapi.IridiumColorAPI;

import dev._2lstudios.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandExecutor {
    private Economy plugin;
    private CommandSender sender;
    
    public CommandExecutor(Economy plugin, CommandSender sender) {
        this.plugin = plugin;
        this.sender = sender;
    }

    public String formatMessage(String message) {
        return IridiumColorAPI.process(
            ChatColor.translateAlternateColorCodes('&', message)
                .replace("{plugin_version}", this.plugin.getDescription().getVersion())
        );
    }

    public String getLang() {
        return this.plugin.getLanguageManager().getDefaultLocale();
    }

    public String getI18nMessage(String key) {
        String lang = this.getLang();
        String message = this.plugin.getLanguageManager()
            .getLanguage(lang)
            .getString(key);

        if (message == null) {
            return "<missing translation key \"" + key + "\"> in lang " + lang + ">";
        } else {
            return message;
        }
    }

    public void sendMessage(String message) {
        this.sender.sendMessage(this.formatMessage(message));
    }

    public void sendI18nMessage(String key) {
        this.sendMessage(this.getI18nMessage(key));
    }

    public boolean isPlayer() {
        return this.sender instanceof Player;
    }

    public boolean hasPermission(String permission) {
        if (this.isPlayer()) {
            return this.sender.hasPermission(permission);
        } else {
            return true;
        }
    }

    public Economy getPlugin() {
        return this.plugin;
    }
}