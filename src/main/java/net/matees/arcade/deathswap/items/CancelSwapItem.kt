package net.matees.arcade.deathswap.items

import me.kodysimpson.simpapi.colors.ColorTranslator
import net.matees.Arcade
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class CancelSwapItem {

    fun getItem(): ItemStack {
        val item = ItemStack(Material.BARRIER)
        val meta = item.itemMeta
        meta.setDisplayName(
            ColorTranslator.translateColorCodes(
                "§cOpt Out"
            )
        )
        meta.lore = listOf(
            ColorTranslator.translateColorCodes(
                "§eRight Clicking allows you to opt out of this swap"
            ),
            ColorTranslator.translateColorCodes(
                "§4§oItem will disappear after use!"
            )
        )
        meta.persistentDataContainer.set(
            NamespacedKey(Arcade.plugin!!, "cancel_swap"),
                PersistentDataType.STRING,
                "cancel_swap",
        )

        item.setItemMeta(meta)

        return item
    }

    companion object {
        val instance: CancelSwapItem  = CancelSwapItem()
    }
}