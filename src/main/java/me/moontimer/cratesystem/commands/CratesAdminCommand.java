package me.moontimer.cratesystem.commands;

import me.moontimer.cratesystem.Crates;
import me.moontimer.cratesystem.create.Crate;
import me.moontimer.cratesystem.create.CrateManager;
import me.moontimer.cratesystem.create.rarity.Rarity;
import me.moontimer.cratesystem.create.rarity.RarityManager;
import me.moontimer.cratesystem.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class CratesAdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("crate.admin")) {
            player.sendMessage(Crates.getPrefix() + "§7Du hast auf diesen Befehl §4keine §7Rechte");
            return true;
        }


        //ca create commonName DisplayName
        //ca addItem commonName rarityName
        //ca edit commonName

        switch (args.length) {
            case 2:
                if (args[0].equalsIgnoreCase("removeItems")) {
                    //player.openInventory(Objects.requireNonNull(CrateManager.getCrateFromName(args[1])).crateEditInventory());
                    player.sendMessage("edit inv geöffnet");
                }
                break;
            case 3:
                if (args[0].equalsIgnoreCase("create")) {
                    new Crate(args[1], args[2]).create();
                    player.sendMessage(Crates.getPrefix() + "§7Du hast erfolgreich ein Create erstellt.");
                } else if (args[0].equalsIgnoreCase("addItem")) {
                    CrateManager.getCrateFromName(args[1]).addItem(player.getInventory().getItemInMainHand(), RarityManager.getRarityByCommonName(args[2]));
                    player.sendMessage(Crates.getPrefix() + "Du hast erfolgreich ein Item in das Crate hinzugefügt");
                }
                break;
        }

            //test test
        if (args[0].equalsIgnoreCase("test")) {
            Crate crate = CrateManager.getCrateFromName("Test");
            crate.addItem(new ItemBuilder(Material.CHEST, "§aDuKleiner").build(), Rarity.Common);
            player.sendMessage("abgeschlossen");
        }


        return false;
    }
}
