package net.matees.arcade.itemrush.listeners

import net.matees.arcade.itemrush.ItemRush
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class BlockBreak : Listener {
    companion object {
        private val validItems = Material.entries.filter { it.isItem }
    }

    @EventHandler
    fun onBreakBlock(event: BlockBreakEvent) {
        if (!ItemRush.instance.isCurrentMinigame) return

        event.isCancelled = true
        event.block.type = Material.AIR

        val itemRush = ItemRush.instance
        val maxItemCount = itemRush.getSetting("Max Item Count")?.setting as? Int ?: 64
        val randomItemCount = itemRush.getSetting("Random Item Count")?.setting as? Boolean ?: false

        val drop = getUniqueDrop(randomItemCount, maxItemCount)

        event.block.world.dropItemNaturally(event.block.location, drop)
    }

    private fun getUniqueDrop(randomItemCount: Boolean, maxItemCount: Int): ItemStack {
        val material = validItems.random()
        val item = ItemStack(material)

        item.amount = if (randomItemCount) {
            Random.nextInt(1, maxItemCount + 1)
        } else {
            maxItemCount
        }

        return item
    }
}