package dev._2lstudios.economy.errors;

public class WorldNotFoundException extends Exception {
    private String world;
    
    public WorldNotFoundException(String world) {
        super("World " + world + " doesn't exist or is not loaded.");
        this.world = world;
    }
  
    public String getWorldName() {
        return this.world;
    }
}