package dev._2lstudios.economy.errors;

public class MinBalanceLimitReachedException extends Exception {
    private double min;

    public MinBalanceLimitReachedException(double min) {
        super("Account exceeded min balance (" + String.valueOf(min) + ")");
        this.min = min;
    }

    public double getMin() {
        return this.min;
    }
}
