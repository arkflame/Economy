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
        TransactionEntity transactionEntity = new TransactionEntity(from, to, amount, time);

        return transactionEntity;
    }
}
