package me.moontimer.cratesystem.create;

import me.moontimer.cratesystem.Crates;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Map;
import java.util.Set;

public class CrateManager {

    private static final YamlConfiguration config = Crates.getInstance().getCreateConfig().getConfig();
    public static Crate getCrateFromName(String name) {
        if (crateExists(name)) {
            return new Crate(name);
        }
        return null;
    }

    public static boolean crateExists(String name) {
        if(config.get("Crates") == null) {
            return false;
        }
        Set<String> crateNames = config.getConfigurationSection("Crates").getKeys(false);
        return crateNames.contains(name);
    }
}
