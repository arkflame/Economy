package dev._2lstudios.economy.errors;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() {
        super("Account not found.");
    }
}
