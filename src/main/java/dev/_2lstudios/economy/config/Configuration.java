package dev._2lstudios.economy.config;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import dev._2lstudios.economy.utils.BukkitUtils;

public class Configuration extends YamlConfiguration {
    private File file;
    private String raw;
    private Configuration failover;

    public Configuration(String raw) {
        this.raw = raw;
    }
    
    public Configuration(File file, Configuration failover) {
        this.file = file;
        this.failover = failover;
    }

    public Configuration(final File file) {
        this.file = file;
    }

    /* Utils methods */
    public boolean create() throws IOException {
        if (!this.exists()) {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            return this.file.createNewFile();
        } else {
            return false;
        }
    }

    public void delete() {
        if (this.file.exists()) {
            this.file.delete();
        }
    }

    public boolean exists() {
        return this.file.exists();
    }

    public void load() throws FileNotFoundException, IOException, InvalidConfigurationException {
        if (this.raw != null) {
            this.loadFromString(this.raw);
        } else {
            this.load(this.file);
        }
    }

    public void save() throws IOException {
        this.save(this.file);
    }

    public void safeSave() {
        try {
            this.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Fail-proof methods */
    public void setIfNotExist(String key) {
        if (!this.contains(key) && this.failover != null) {
            Object candidate = this.failover.get(key);
            if (candidate != null) {
                this.set(key, candidate);
                this.safeSave();
            }
        }
    }

    /* Primitive object get and set */
    @Override
    public int getInt(String key) {
        this.setIfNotExist(key);
        return super.getInt(key);
    }

    @Override
    public String getString(String key) {
        this.setIfNotExist(key);
        return super.getString(key);
    }

    @Override
    public boolean getBoolean(String key) {
        this.setIfNotExist(key);
        return super.getBoolean(key);
    }

    public List<String> getStringList(String key) {
        this.setIfNotExist(key);
        return super.getStringList(key);
    }

    /* Custom object get */
    public Location getLocation(String key) {
        this.setIfNotExist(key);

        if (!key.isEmpty()) {
            key += ".";
        }

        String worldName = this.getString(key + "world");
        double x = this.getDouble(key + "x");
        double y = this.getDouble(key + "y");
        double z = this.getDouble(key + "z");
        float yaw = (float) this.getDouble(key + "yaw");
        float pitch = (float) this.getDouble(key + "pitch");

        if (worldName != null) {
            return new Location(Bukkit.getWorld(worldName),x, y, z, yaw, pitch);
        } else {
            return null;
        }
    }


    public Location getLocation() {
        return this.getLocation("");
    }

    public Material getMaterial(String key) {
        this.setIfNotExist(key);

        String name = this.getString(key);

        if (name.isEmpty()) {
            return null;
        }

        return BukkitUtils.getMaterial(name);
    }

    public Sound getSound(final String key) {
        this.setIfNotExist(key);

        String name = this.getString(key);

        if (name.isEmpty()) {
            return null;
        }

        return BukkitUtils.getSound(name);
    }

    /* Custom object set */
    public void setLocation(String key, final Location location, final boolean includeWorld) {
        if (!key.isEmpty()) {
            key += ".";
        }

        if (includeWorld) {
            this.set(key + "world", location.getWorld().getName());
        }

        this.set(key + "x", location.getX());
        this.set(key + "y", location.getY());
        this.set(key + "z", location.getZ());
        this.set(key + "pitch", location.getPitch());
        this.set(key + "yaw", location.getYaw());
    }

    public void setLocation(String key, Location location) {
        this.setLocation(key, location, true);
    }

    public void setLocation(final Location location) {
        this.setLocation("", location, true);
    }

    public void setMaterial(String key, Material material) {
        this.set(key, material.name());
    }

    public void setSound(String key, Sound sound) {
        this.set(key, sound.name());
    }
}