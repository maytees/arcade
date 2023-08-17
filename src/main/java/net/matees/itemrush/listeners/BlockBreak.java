package net.matees.itemrush.listeners;

import net.matees.itemrush.ItemRush;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BlockBreak implements Listener {
    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        ItemRush itemRush = ItemRush.getInstance();
        int maxItemCount = (int) itemRush.getSetting("Max Item Count").getSetting();
        boolean randomItemCount = (boolean) itemRush.getSetting("Random Item Count").getSetting();

        Random random = new Random();

        int maxAmount = random.nextInt(maxItemCount);
        int material = random.nextInt(Material.values().length);
        int amount = randomItemCount ? random.nextInt(maxAmount) : 1;
        ItemStack randomItem = new ItemStack(Material.values()[material], Math.max(Material.values()[material].getMaxStackSize(), amount));
        event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), randomItem);
    }
}
