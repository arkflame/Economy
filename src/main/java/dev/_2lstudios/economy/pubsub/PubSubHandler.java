package dev._2lstudios.economy.pubsub;

import dev._2lstudios.economy.entities.PlayerDataEntity;
import dev._2lstudios.economy.players.EconomyPlayer;
import dev._2lstudios.economy.plugins.EconomyPlugin;
import dev._2lstudios.economy.pubsub.packets.BalanceSyncPacket;

public class PubSubHandler {
    private EconomyPlugin plugin;

    public PubSubHandler(EconomyPlugin plugin) {
        this.plugin = plugin;
    }

    public void handle(BalanceSyncPacket packet) {
        PlayerDataEntity cachedAccount = this.plugin.getPlayerDataManager()
            .getCachedAccount(packet.getAccountId());

        // Update balance if it is cached.
        if (cachedAccount != null && cachedAccount.balance != packet.getNewBalance()) {
            cachedAccount.balance = packet.getNewBalance();
        }

        // Notify to the player.
        EconomyPlayer player = this.plugin.getPlayerManager().getPlayer(packet.getPlayerName());
        if (player != null) {
            boolean isTake = packet.getNewBalance() < packet.getOldBalance();
            double diff = Math.abs(packet.getNewBalance() - packet.getOldBalance());

            if (isTake) {
                player.sendMessage("&c-" + diff + "$");
            } else {
                player.sendMessage("&a+" + diff + "$");
            }
        }
    }
}
