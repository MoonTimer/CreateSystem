package me.moontimer.cratesystem.create.item;

import me.moontimer.cratesystem.create.rarity.Rarity;
import org.bukkit.inventory.ItemStack;

public class Item {

    private final ItemStack itemStack;
    private final Rarity rarity;

    public Item(ItemStack itemStack, Rarity rarity) {
        this.itemStack = itemStack;
        this.rarity = rarity;
    }

}
