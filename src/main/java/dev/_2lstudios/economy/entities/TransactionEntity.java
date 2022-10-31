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

    public TransactionEntity(String fromId, String fromName, String fromUUID, String toId, String toName, String toUUID,
            double amount, long time) {
        this.fromId = fromId;
        this.fromName = fromName;
        this.fromUUID = fromUUID;
        this.toId = toId;
        this.toName = toName;
        this.toUUID = toUUID;
        this.amount = amount;
        this.time = time;
    }

    public TransactionEntity(TransactionMember from, TransactionMember to, double amount, long time) {
        this(from.getId(), from.getName(), from.getUuid(), to.getId(), to.getName(), to.getUuid(), amount, time);
    }

    public Transaction toTransaction() {
        return new Transaction(new TransactionMember(fromId, fromUUID, fromName),
                new TransactionMember(toId, toUUID, toName), amount, time);
    }
}
