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
        private val blockTypeDrops = mutableMapOf<Material, ItemStack>()
        private val usedItems = mutableSetOf<Material>()
    }

    @EventHandler
    fun onBreakBlock(event: BlockBreakEvent) {
        if (!ItemRush.instance.isCurrentMinigame) return
        event.isCancelled = true
        val brokenBlockType = event.block.type
        event.block.type = Material.AIR

        val itemRush = ItemRush.instance
        val maxItemCount = itemRush.getSetting("Max Item Count")?.setting as? Int ?: 64
        val randomItemCount = itemRush.getSetting("Random Item Count")?.setting as? Boolean ?: false
//        val x = itemRush.getSetting("Variable X")?.setting as? Boolean ?: false

        val drop = if (true) {
            // Unique drops per block type
            blockTypeDrops.getOrPut(brokenBlockType) { getUniqueDrop(randomItemCount, maxItemCount) }
        } else {
            // Random drop every time
            getRandomDrop(randomItemCount, maxItemCount)
        }

        event.block.world.dropItemNaturally(event.block.location, drop.clone())
    }

    private fun getUniqueDrop(randomItemCount: Boolean, maxItemCount: Int): ItemStack {
        var material: Material
        do {
            material = validItems.random()
        } while (material in usedItems && usedItems.size < validItems.size)

        usedItems.add(material)
        return createItemStack(material, randomItemCount, maxItemCount)
    }

    private fun getRandomDrop(randomItemCount: Boolean, maxItemCount: Int): ItemStack {
        val material = validItems.random()
        return createItemStack(material, randomItemCount, maxItemCount)
    }

    private fun createItemStack(material: Material, randomItemCount: Boolean, maxItemCount: Int): ItemStack {
        val item = ItemStack(material)
        item.amount = if (randomItemCount) {
            Random.nextInt(1, maxItemCount + 1)
        } else {
            maxItemCount
        }
        return item
    }
}