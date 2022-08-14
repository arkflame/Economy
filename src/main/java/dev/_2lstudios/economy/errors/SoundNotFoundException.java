package dev._2lstudios.economy.errors;

public class SoundNotFoundException extends Exception {
    private String sound;
    
    public SoundNotFoundException(String sound) {
        super("Material " + sound + " doesn't exist.");
        this.sound = sound;
    }
  
    public String getSoundName() {
        return this.sound;
    }
}