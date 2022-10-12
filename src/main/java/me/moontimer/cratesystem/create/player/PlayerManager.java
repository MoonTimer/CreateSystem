package me.moontimer.cratesystem.create.player;

import me.moontimer.cratesystem.Crates;
import me.moontimer.cratesystem.create.Crate;
import me.moontimer.cratesystem.create.CrateOpening;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlayerManager {

    private final Player player;

    public PlayerManager(Player player) {
        this.player = player;
    }

    //noch ältere idee
    public void openCrate(final Crate crate) {
        Inventory inventory = Bukkit.createInventory(null, 27, "§aCrate" + crate.getDisplayNameFromConfig());
        player.openInventory(inventory);
        new CrateOpening(player, inventory, crate);
    }


    /**
    public void openCreate(Crate crate){
        List<ItemStack> items = new ArrayList<>();
        Set<ItemStack> itemsNonChance = crate.getItemsAndWeights().keySet();
        List<ItemStack> itemList = new ArrayList();
        itemList.addAll(itemsNonChance);
        for (ItemStack itemStack : itemList) {
            double dupe = crate.getItemsAndWeights().get(itemStack);
            switch ((int) dupe) {
                case 1:
                    for (int i = 0; i < 9; i++) {
                        items.add(itemStack);
                    }
                    break;
                case 2:
                    for (int i = 0; i < 8; i++) {
                        items.add(itemStack);
                    }
                    break;
                case 3:
                    for (int i = 0; i < 7; i++) {
                        items.add(itemStack);
                    }
                    break;
                case 4:
                    for (int i = 0; i < 6; i++) {
                        items.add(itemStack);
                    }
                    break;
                case 5:
                    for (int i = 0; i < 5; i++) {
                        items.add(itemStack);
                    }
                    break;
                case 6:
                    for (int i = 0; i < 4; i++) {
                        items.add(itemStack);
                    }
                    break;
                case 7:
                    for (int i = 0; i < 3; i++) {
                        items.add(itemStack);
                    }
                    break;
                case 8:
                    for (int i = 0; i < 2; i++) {
                        items.add(itemStack);
                    }
                    break;
                case 9:
                    items.add(itemStack);
                    break;
            }
        }

        ItemStack placeholder1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = placeholder1.getItemMeta();
        meta.setDisplayName("...");
        placeholder1.setItemMeta(meta);
        final ItemStack placeholder = new ItemStack(placeholder1);
        placeholder.setDurability((short) 15);
        final Inventory inv = Bukkit.createInventory(null, 45, ChatColor.GREEN + "Opening Crate...");
        ItemStack item = player.getItemInHand();
        if(item.getAmount() == 1) {
            player.getInventory().removeItem(item);
        }else {
            item.setAmount(item.getAmount() - 1);
            player.updateInventory();
        }
        player.openInventory(inv);



        new BukkitRunnable() {
            int startA = 0;
            int startB = 44;

            public void run() {
                ItemStack[] toAdd = chooseRandomItems(crateName);
                WeightedRa
                int slot = 22;
                int amtCopy = toAdd.length;
                while(amtCopy > 2) {
                    slot--;
                    amtCopy -= 2;
                }
                for(int i = 0; i < toAdd.length; i++) {
                    int random = (int) (Math.random() * ((items.size()) + 1));
                    inv.setItem(slot, toAdd[i]);
                    slot++;
                    player.getInventory().addItem(toAdd[i]);
                    String message = plugin.getConfig().getString("Messages.ItemReceived");
                    String name = "item";
                    if(toAdd[i].getItemMeta().hasDisplayName()) {
                        name = toAdd[i].getItemMeta().getDisplayName();
                    }else {
                        name = toAdd[i].getType().toString();
                    }
                    message = message.replace("%item_name%", name);
                    message = message.replace("%item_chance%", String.valueOf(getChance(crateName, toAdd[0])));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                }

                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    public void run() {
                        if(player.getOpenInventory() != null) {
                            if(player.getOpenInventory().getTitle().equals(ChatColor.GREEN + "Opening Crate...")) {
                                player.closeInventory();
                            }
                        }
                    }
                }, 40L);

                cancel();
            }

        }.runTaskTimer(plugin, 2L, 2L);
    }*/
}
