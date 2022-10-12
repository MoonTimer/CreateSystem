package me.moontimer.cratesystem;

import lombok.Getter;
import me.moontimer.cratesystem.commands.CratesAdminCommand;
import me.moontimer.cratesystem.commands.CratesCommand;
import me.moontimer.cratesystem.listener.InventoryClickListener;
import me.moontimer.cratesystem.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Crates extends JavaPlugin {

    @Getter
    private static Crates instance;

    @Getter
    private Config createConfig;

    @Getter
    private static final String prefix = "§2PixelCrates §8| ";

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        createConfig = new Config("crates.yml", this);
        getCommand("crates").setExecutor(new CratesCommand());
        getCommand("cratesadmin").setExecutor(new CratesAdminCommand());
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
