package me.moontimer.cratesystem.create;

import me.moontimer.cratesystem.Crates;
import me.moontimer.cratesystem.create.rarity.Rarity;
import me.moontimer.cratesystem.utils.Config;
import me.moontimer.cratesystem.utils.ItemStackSerialization;
import me.moontimer.cratesystem.utils.WeightedRandomList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Crate {

    private final Config configClass = Crates.getInstance().getCreateConfig();
    private final YamlConfiguration config = configClass.getConfig();
    private String displayName;
    private final String commonName;

    public Crate(String commonName) {
        this.commonName = commonName;
    }
    public Crate(String displayName, String commonName) {
        this.displayName = displayName;
        this.commonName = commonName;
    }

    public void create() {
        config.set("Crates." + commonName + ".displayName", displayName);
        Crates.getInstance().getCreateConfig().save();
    }

    public void addItem(ItemStack itemStack, Rarity rarity) {

        if (CrateManager.crateExists(commonName)) {
            if (containsItem(itemStack)) {
                return;
            }

            String itemToBase64 = ItemStackSerialization.itemStackArrayToBase64(new ItemStack[] {itemStack});
            String itemAndWeight = null;

            switch (rarity) {
                case Common:
                    itemAndWeight = itemToBase64  + "///:::///" + 1;
                    break;
                case Uncommon:
                    itemAndWeight = itemToBase64  + "///:::///" + 2;
                    break;
                case Rare:
                    itemAndWeight = itemToBase64  + "///:::///" + 3;
                    break;
                case Epic:
                    itemAndWeight = itemToBase64  + "///:::///" + 4;
                    break;
                case Mystic:
                    itemAndWeight = itemToBase64  + "///:::///" + 5;
                    break;
                case Exclusive:
                    itemAndWeight = itemToBase64  + "///:::///" + 6;
                    break;
                case Limited:
                    itemAndWeight = itemToBase64  + "///:::///" + 7;
                    break;
                case MainWin:
                    itemAndWeight = itemToBase64  + "///:::///" + 8;
                    break;
                case MegaMainWin:
                    itemAndWeight = itemToBase64  + "///:::///" + 9;
                    break;
            }

            List<String> list = new ArrayList();
            if (config.get("Crates." + commonName + ".rewards") == null) {
                list.add(itemAndWeight);
            } else {
                list = config.getStringList("Crates." + commonName + ".rewards");
                list.add(itemAndWeight);
            }
            config.set("Crates." + commonName + ".rewards", list);
            configClass.save();
        }

        //Alte Funktion mit neuere Verbessert
        /**
        if (CrateManager.crateExists(commonName)) {
            int i = 0;
            if (config.getConfigurationSection(commonName + ".content") != null) {
                Map<String, Object> items = Objects.requireNonNull(config.getConfigurationSection(commonName + ".content")).getValues(true);
                for (Map.Entry<String, Object> entry : items.entrySet()) {
                    String key = entry.getKey();
                    if (!key.contains(".type") && !key.contains(".meta") && !key.contains(".amount") && !key.contains(".damage") && !key.contains(".broadcast")) {
                        i += 1;
                    }
                }
            }
            i += 1;
            config.set(commonName + ".content." + i + ".item", itemStack.serialize());
            config.set(commonName + ".content." + i + ".rarity", Objects.requireNonNull(RarityManager.getRarityByCommonName(rarityName)).getCommonName());
            Crates.getInstance().getCreateConfig().save();
        }
         */

    }

    public Inventory createRemoveInventory() {
        if(CrateManager.crateExists(commonName)) {
            if(getItemsAndWeights() == null) {
                Inventory inv = Bukkit.createInventory(null, 9, "§7Edit " + ChatColor.BOLD + displayName);
                return inv;
            }
            Set<ItemStack> items = getItemsAndWeights().keySet();
            List<ItemStack> itemList = new ArrayList();
            itemList.addAll(items);
            double itemCount = itemList.size();
            double rows = itemCount / 9;
            int rowsInt = (int) Math.ceil(rows);
            if(rowsInt == rows) {
                rowsInt += 1;
            }
            int slots = rowsInt * 9;
            // 54 is the max size for an inventory (6 * 9 = 54)
            if(slots > 54) {
                slots = 54;
            }
            Inventory inv = Bukkit.createInventory(null, slots, ChatColor.GREEN + "Edit " + ChatColor.BOLD + displayName);
            for(int i = 0; i < itemList.size(); i++) {
                ItemStack item = itemList.get(i);
                List<String> lore = new ArrayList();
                if(item.getItemMeta().hasLore()) {
                    lore = item.getItemMeta().getLore();
                    lore.add(" ");
                }
                lore.add(ChatColor.GREEN + "CHANCE: " + getItemsAndWeights().get(item) + "%");
                lore.add(ChatColor.YELLOW + "RIGHT-CLICK TO REMOVE");
                lore.add(ChatColor.YELLOW + "SHIFT-CLICK TO EDIT CHANCE");
                ItemMeta meta = item.getItemMeta();
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(i, item);
            }
            return inv;
        }
        return null;
    }

    public WeightedRandomList<ItemStack> getItems() {
        double totalChance = 0;
        if(CrateManager.crateExists(commonName)) {
            if(getItemsAndWeights() != null) {
                WeightedRandomList<ItemStack> randomList = new WeightedRandomList<ItemStack>();
                for(ItemStack item : getItemsAndWeights().keySet()) {
                    randomList.addEntry(item, getItemsAndWeights().get(item));
                    totalChance += getItemsAndWeights().get(item);
                }
                if(totalChance < 100d) {
                    ItemStack air = new ItemStack(Material.AIR);
                    randomList.addEntry(air, 100d - totalChance);
                }
                return randomList;
            }
        }
        return null;
    }


    public ItemStack[] chooseRandomItems() {
        if(CrateManager.crateExists(commonName)) {
            WeightedRandomList<ItemStack> randomList = getItems();
            int amt = 10;
            Random r = new Random();
            amt = r.nextInt(50 + 1 - 10) + 10;
            ItemStack[] items = new ItemStack[amt];
            for(int i = 0; i < amt; i++) {
                items[i] = randomList.getRandom();
            }
            return items;
        }
        return null;
    }
    public HashMap<ItemStack, Double> getItemsAndWeights() {
        if(CrateManager.crateExists(commonName)) {
            if(config.get("Crates." + commonName + ".rewards") != null) {
                List<String> list = config.getStringList("Crates." + commonName + ".rewards");
                HashMap<ItemStack, Double> map = new HashMap();
                for(String itemAndWeight : list) {
                    String[] split = itemAndWeight.split("///:::///");
                    String base64Item = split[0];
                    double weight = Double.parseDouble(split[1]);
                    ItemStack[] singleItemArray = ItemStackSerialization.itemStackArrayFromBase64(base64Item);
                    ItemStack item = singleItemArray[0];
                    map.put(item, weight);
                }
                return map;
            }
        }
        return null;
    }

    public boolean containsItem(ItemStack item) {
        if(getItemsAndWeights() == null) {
            return false;
        }else {
            if(getItemsAndWeights().keySet().contains(item)) {
                return true;
            }else {
                return false;
            }
        }
    }

    public String getDisplayName() {
        return displayName;
    }
    public String getDisplayNameFromConfig() {
        return config.getString("Crates." + commonName + ".displayName");
    }

    public String getCommonName() {
        return commonName;
    }
}
