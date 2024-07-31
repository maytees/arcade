package net.matees.settings

import me.kodysimpson.simpapi.colors.ColorTranslator
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

abstract class Setting<T> {
    var menuItem: ItemStack
        get() {
            val item = ItemStack(menuItemMaterial!!, 1)
            val meta = item.itemMeta

            meta.setDisplayName(ColorTranslator.translateColorCodes("&l&6") + this.name)
            meta.lore = listOf(
                ColorTranslator.translateColorCodes("Current: &2" + this.setting),
                ColorTranslator.translateColorCodes("&o&7" + this.description),
                this.settingLore
            )

            item.addUnsafeEnchantment(Enchantment.LUCK_OF_THE_SEA, 1)
            meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS)

            item.setItemMeta(meta)
            return item
        }
        set(value) {}

    abstract val name: String

    abstract val description: String

    // Default
    abstract var setting: T?

    abstract val menuItemMaterial: Material?

    abstract fun setIntValue(value: Int)

    abstract fun setBooleanValue(value: Boolean)

    abstract fun setStringValue(value: String?)

    abstract fun setPlayersValue(value: List<Player?>?)

    abstract val menuItemSlot: Int

    val settingLore: String
        get() {
            if (setting is Int) {
                return ColorTranslator.translateColorCodes(
                    """
    &2Increase (left)
    &4Decrease(right)
    """.trimIndent()
                )
            }

            if (setting is String) {
                return ColorTranslator.translateColorCodes("&3Click to edit")
            }

            if (setting is Boolean) {
                return ColorTranslator.translateColorCodes("&3Click to toggle")
            }

            if (setting is List<*>) {
                return ColorTranslator.translateColorCodes("&3Click to view/edit")
            }

            return ""
        }

    fun handleItemClick(event: InventoryClickEvent) {
        var max = 128
        var jump = 1

        if (event.currentItem!!.type == Material.IRON_BARS) {
            max = 30000000
            jump = 50
        }

        if(!event.currentItem!!.displayName().equals("Time Between")) {
            max = 3600
        }

        if (setting is Int) {
            val current = setting as Int
            if (event.isLeftClick && current != max) {
                this.setIntValue(current + jump)
            } else if (event.isRightClick && current >= 1) {
                this.setIntValue(current - jump)
            }

            return
        }

        if (setting is Boolean) {
            if (!event.isLeftClick) return
            val current = setting as Boolean
            this.setBooleanValue(!current)

            return
        }

        if (setting is String) {
            if (!event.isLeftClick) return
            // Open sign, worry about this later
        }

        if (setting is List<*>) {
            if (!event.isLeftClick) {
            }
            // Open another menu, with a list of "settings", heads, or whatever.
            // Worry about this later
        }
    }
}
