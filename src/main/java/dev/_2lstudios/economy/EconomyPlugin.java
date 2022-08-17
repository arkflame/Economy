package dev._2lstudios.economy;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import com.dotphin.milkshake.Milkshake;
import com.dotphin.milkshake.Provider;

import dev._2lstudios.economy.account.PlayerDataManager;
import dev._2lstudios.economy.api.EconomyAPI;
import dev._2lstudios.economy.api.events.EconomyEvent;
import dev._2lstudios.economy.commands.CommandListener;
import dev._2lstudios.economy.commands.admin.EconomyCommand;
import dev._2lstudios.economy.commands.player.BalanceCommand;
import dev._2lstudios.economy.commands.player.PayCommand;
import dev._2lstudios.economy.config.ConfigManager;
import dev._2lstudios.economy.config.Configuration;
import dev._2lstudios.economy.i18n.LanguageManager;
import dev._2lstudios.economy.listeners.PlayerJoinListener;
import dev._2lstudios.economy.listeners.PlayerQuitListener;
import dev._2lstudios.economy.players.EconomyPlayerManager;
import dev._2lstudios.economy.providers.EconomyProvider;
import dev._2lstudios.economy.pubsub.PubSub;
import dev._2lstudios.economy.pubsub.PubSubHandler;
import net.milkbowl.vault.economy.Economy;

public class EconomyPlugin extends JavaPlugin {
    private EconomyAPI api;

    private Provider provider;
    private PubSub pubsub;

    private ConfigManager configManager;
    private LanguageManager languageManager;
    private PlayerDataManager playerDataManager;
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
    public void onEnable() {
        // Initialize API
        this.api = new EconomyAPI(this);

        // Instantiate managers.
        this.configManager = new ConfigManager(this);
        this.languageManager = new LanguageManager(this);
        this.playerManager = new EconomyPlayerManager(this);

        // Load data.
        this.languageManager.loadLanguagesSafe();
        this.playerManager.addAll();

        // Connect to database.
        String mongoURI = this.getConfig().getString("storage.mongodb.uri");
        String mongoCollection = this.getConfig().getString("storage.mongodb.collection");

        this.provider = Milkshake.connect(mongoURI);
        this.playerDataManager = new PlayerDataManager(this.provider, mongoCollection);

        // Connect to pubsub.
        PubSubHandler handler = new PubSubHandler(this);
        this.pubsub = PubSub.create(this.getConfig(), handler);
        this.pubsub.connect();

        // Register listeners.
        this.addListener(new PlayerJoinListener(this));
        this.addListener(new PlayerQuitListener(this));

        // Register commands.
        this.addCommand(new EconomyCommand());
        this.addCommand(new BalanceCommand());
        this.addCommand(new PayCommand());

        // Register hooks.
        // Hook into Vault
        Bukkit.getServicesManager().register(
            Economy.class,
            new EconomyProvider(this),
            this,
            ServicePriority.Highest);
    }

    @Override
    public void onDisable() {
        this.provider.close();
        this.pubsub.disconnect();
    }

    // Configuration getters
    public Configuration getConfig() {
        return this.configManager.getConfig("config.yml");
    }

    // API getter
    public EconomyAPI getAPI() {
        return this.api;
    }

    // Managers getters
    public LanguageManager getLanguageManager() {
        return this.languageManager;
    }

    public EconomyPlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return this.playerDataManager;
    }

    // Others getters
    public boolean hasPlugin(String pluginName) {
        Plugin plugin = this.getServer().getPluginManager().getPlugin(pluginName);
        return plugin != null && plugin.isEnabled();
    }
}