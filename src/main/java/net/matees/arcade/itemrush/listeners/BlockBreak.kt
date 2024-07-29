package net.matees.arcade.itemrush.listeners

import net.matees.arcade.itemrush.ItemRush
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import java.util.*

class BlockBreak : Listener {
    @EventHandler
    fun onBreakBlock(event: BlockBreakEvent) {
        if (!ItemRush.Companion.instance.isCurrentMinigame) return

        val itemRush: ItemRush = ItemRush.Companion.instance
        val maxItemCount = itemRush.getSetting("Max Item Count")?.setting as Int
        val randomItemCount = itemRush.getSetting("Random Item Count")!!.setting as Boolean

        val material = Material.entries[Random().nextInt(Material.entries.size)]
        val drop = getUniqueDrop(material, randomItemCount, maxItemCount)

        event.block.world.dropItemNaturally(event.block.location, drop)
    }

    private fun getUniqueDrop(blockType: Material, randomItemCount: Boolean, maxItemCount: Int): ItemStack {
        val item = ItemStack(blockType)

        if (randomItemCount) {
            val itemCount = Random().nextInt(maxItemCount + 1)
            item.amount = itemCount

            return item
        }

        item.amount = maxItemCount
        return item
    }
}
