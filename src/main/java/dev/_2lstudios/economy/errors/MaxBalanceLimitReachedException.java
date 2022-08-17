package dev._2lstudios.economy.errors;

public class MaxBalanceLimitReachedException extends Exception {
    private double max;

    public MaxBalanceLimitReachedException(double max) {
        super("Account exceeded max balance (" + String.valueOf(max) + ")");
        this.max = max;
    }

    public double getMax() {
        return this.max;
    }
}
