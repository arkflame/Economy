package dev._2lstudios.economy.entities;

import com.dotphin.classserializer.annotations.Serializable;

import dev._2lstudios.economy.Transaction;
import dev._2lstudios.economy.TransactionMember;

@Serializable
public class TransactionEntity {
    public String fromId;
    public String fromName;
    public String fromUUID;

    public String toId;
    public String toName;
    public String toUUID;

    public double amount;
    public long time;

    public Transaction toTransaction() {
        return new Transaction(new TransactionMember(fromId, fromUUID, fromName), new TransactionMember(toId, toUUID, toName), amount, time);
    }
}
