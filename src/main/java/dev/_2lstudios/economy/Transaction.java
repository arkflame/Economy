package dev._2lstudios.economy;

import dev._2lstudios.economy.entities.TransactionEntity;

public class Transaction {
    private TransactionMember from;
    private TransactionMember to;
    private double amount;
    private long time;

    public Transaction(TransactionMember from, TransactionMember to, double amount, long time) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.time = time;
    }

    public TransactionMember getFrom() {
        return from;
    }

    public TransactionMember getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    public long getTime() {
        return time;
    }

    public TransactionEntity toTransactionEntity() {
        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.time = time;
        transactionEntity.amount = amount;
        transactionEntity.fromId = from.getId();
        transactionEntity.fromName = from.getName();
        transactionEntity.fromUUID = from.getUuid();
        transactionEntity.toId = to.getId();
        transactionEntity.toName = to.getName();
        transactionEntity.toUUID = to.getUuid();

        return transactionEntity;
    }
}
