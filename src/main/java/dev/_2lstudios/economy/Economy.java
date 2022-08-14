package dev._2lstudios.economy;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.economy.api.EconomyAPI;
import dev._2lstudios.economy.api.events.EconomyEvent;
import dev._2lstudios.economy.commands.CommandListener;
import dev._2lstudios.economy.commands.impl.HelloCommand;
import dev._2lstudios.economy.config.ConfigManager;
import dev._2lstudios.economy.config.Configuration;
import dev._2lstudios.economy.i18n.LanguageManager;
import dev._2lstudios.economy.listeners.PlayerJoinListener;
import dev._2lstudios.economy.listeners.PlayerQuitListener;
import dev._2lstudios.economy.players.EconomyPlayerManager;

public class Economy extends JavaPlugin {
    private ConfigManager configManager;
    private LanguageManager languageManager;
    private EconomyPlayerManager playerManager;

    private void addCommand(CommandListener command) {
        command.register(this, false);
    }

    private void addListener(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }
    
    public boolean callEvent(EconomyEvent event) {
        this.getServer().getPluginManager().callEvent(event);
        return !event.isCancelled();
    }
    
    @Override
    public void onEnable () {
        // Initialize API
        new EconomyAPI(this);

        // Instantiate managers.
        this.configManager = new ConfigManager(this);
        this.languageManager = new LanguageManager(this);
        this.playerManager = new EconomyPlayerManager(this);

        // Load data.
        this.languageManager.loadLanguagesSafe();
        this.playerManager.addAll();

        // Register listeners.
        this.addListener(new PlayerJoinListener(this));
        this.addListener(new PlayerQuitListener(this));

        // Register commands.
        this.addCommand(new HelloCommand());
    }

    // Configuration getters
    public Configuration getConfig() {
        return this.configManager.getConfig("config.yml");
    }

    // Managers getters
    public LanguageManager getLanguageManager() {
        return this.languageManager;
    }

    public EconomyPlayerManager getPlayerManager() {
        return this.playerManager;
    }

    // Others getters
    public boolean hasPlugin(String pluginName) {
        Plugin plugin = this.getServer().getPluginManager().getPlugin(pluginName);
        return plugin != null && plugin.isEnabled();
    }
}