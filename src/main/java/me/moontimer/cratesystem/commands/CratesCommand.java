package me.moontimer.cratesystem.commands;

import me.moontimer.cratesystem.create.Crate;
import me.moontimer.cratesystem.create.player.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CratesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //openCreateGui
        Player player = (Player) sender;

        new PlayerManager(player).openCrate(new Crate("Test"));
        return false;
    }
}
