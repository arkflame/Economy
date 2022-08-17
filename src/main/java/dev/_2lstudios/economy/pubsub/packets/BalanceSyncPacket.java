package dev._2lstudios.economy.pubsub.packets;

public class BalanceSyncPacket extends Packet {
    private String playerName;
    private String accountId;
    private double oldBalance;
    private double newBalance;

    public BalanceSyncPacket(String playerName, String accountId, double oldBalance, double newBalance) {
        this.playerName = playerName;
        this.accountId = accountId;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public double getOldBalance() {
        return this.oldBalance;
    }

    public double getNewBalance() {
        return this.newBalance;
    }
}
