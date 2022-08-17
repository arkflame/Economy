package dev._2lstudios.economy.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;

public class BukkitUtils {
    private static final Sound[] SOUNDS = Sound.values();
    private static final Material[] MATERIALS = Material.values();
    
    public static Sound getSound(String name) {
        for (final Sound sound : SOUNDS) {
            if (name.equalsIgnoreCase(sound.name())) {
                return sound;
            }
        }

        Bukkit.getLogger().warning("Couldn't load sound '" + name + "' (Invalid name?)");
        return null;
    }

    public static Material getMaterial(String name) {
        for (final Material mat : MATERIALS) {
            if (name.equals(mat.name())) {
                return mat;
            }
        }

        Bukkit.getLogger().warning("Couldn't load material '" + name + "' (Invalid name?)");
        return null;
    }
}
