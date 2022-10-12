package me.moontimer.cratesystem.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Config {
    private final File file;
    private final YamlConfiguration config;

    public Config(String name, Plugin plugin) {
        file = new File(plugin.getDataFolder(), name);

        if(!file.exists()) {
            file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        config = new YamlConfiguration();
        try { config.load(file); } catch(Exception e) { e.printStackTrace(); }
    }

    public void save() { try { config.save(file); } catch(Exception e) { e.printStackTrace(); } }

    public File getFile() { return file; }
    public YamlConfiguration getConfig() { return config; }
}
