package net.matees.arcade.itemrush.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import net.matees.arcade.itemrush.ItemRush;

import java.util.Random;

public class BlockBreak implements Listener {
    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        if (!ItemRush.getInstance().isCurrentMinigame())
            return;

        ItemRush itemRush = ItemRush.getInstance();
        int maxItemCount = (int) itemRush.getSetting("Max Item Count").getSetting();
        boolean randomItemCount = (boolean) itemRush.getSetting("Random Item Count").getSetting();

        Material material = Material.values()[new Random().nextInt(Material.values().length)];
        ItemStack drop = getUniqueDrop(material, randomItemCount, maxItemCount);

        if (drop != null)
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
    }

    private ItemStack getUniqueDrop(Material blockType, boolean randomItemCount, int maxItemCount) {
        ItemStack item = new ItemStack(blockType);

        if (randomItemCount) {
            int itemCount = new Random().nextInt(maxItemCount + 1);
            item.setAmount(itemCount);

            return item;
        }

        item.setAmount(maxItemCount);
        return item;
    }
}
