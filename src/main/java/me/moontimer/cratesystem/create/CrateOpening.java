package me.moontimer.cratesystem.create;

import me.moontimer.cratesystem.Crates;
import me.moontimer.cratesystem.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CrateOpening implements Runnable {

    private final Player player;
    private final Inventory inventory;
    private final Crate crate;
    private final BukkitTask bukkitTask;
    private int count = 0;
    private ItemStack win;

    public CrateOpening(Player player, Inventory inventory, Crate crate) {
        this.player = player;
        this.inventory = inventory;
        this.crate = crate;

        this.bukkitTask = Crates.getInstance().getServer().getScheduler().runTaskTimerAsynchronously(Crates.getInstance(), this, 4L, 4L);
        player.setMetadata("crate", new FixedMetadataValue(Crates.getInstance(), crate));
    }

    @Override
    public void run() {
        Random random = new Random();
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

        inventory.setItem(0, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, "").build());
        inventory.setItem(1, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, "").build());
        inventory.setItem(2, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, "").build());
        inventory.setItem(3, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, "").build());
        inventory.setItem(4, new ItemBuilder(Material.HOPPER, "Gewinn").build());
        inventory.setItem(5, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, "").build());
        inventory.setItem(6, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, "").build());
        inventory.setItem(7, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, "").build());
        inventory.setItem(8, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, "").build());

        for (int i = 18; i < 26; i++) {
            inventory.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, "").build());
        }

        ItemStack itemStack = items.get(random.nextInt(items.size()));

        for (int i = 10; i != 18; i++) {
            if (inventory.getItem(i) != null) {
                inventory.setItem(i - 1, inventory.getItem(i));
            }
        }

        if (!player.isOnline()) {
            bukkitTask.cancel();
        }
        inventory.setItem(17, itemStack);
        count++;
        if (count == 26) {
            this.win = itemStack;
            player.setMetadata("cratewinning", new FixedMetadataValue(Crates.getInstance(), win));
        }
        if (count == 30) {
            bukkitTask.cancel();
            for (int i = 9; i != 18; i++) {
                if(i != 13) {
                    inventory.setItem(i, null);
                }
            }
            player.getInventory().addItem(win);
            player.removeMetadata("crate", Crates.getInstance());
            player.removeMetadata("cratewinning", Crates.getInstance());
        }
    }
}
