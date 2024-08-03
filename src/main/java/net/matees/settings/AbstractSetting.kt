package net.matees.settings

import me.kodysimpson.simpapi.colors.ColorTranslator
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

abstract class AbstractSetting<T>(val settingType: SettingType) {
    var menuItem: ItemStack
        get() {
            val item = ItemStack(menuItemMaterial!!, 1)
            val meta = item.itemMeta

            meta.setDisplayName(ColorTranslator.translateColorCodes("&l&6") + this.name)
            meta.lore = listOf(
                if (settingType == SettingType.PlayerBooleanSetting)
                    ColorTranslator.translateColorCodes("Current:&2 ${
                        (this.setting as HashMap<Player, Boolean>)
                            .filter { it.value }
                            .map { it.key.name }
                            .joinToString(", ")
                            .ifEmpty { "None" }
                    }")
                else
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

    open fun setIntValue(value: Int) {}

    open fun setBooleanValue(value: Boolean) {}

    open fun setStringValue(value: String?) {}

    open fun setPlayerBooleanSetting(value: HashMap<Player, Boolean>?) {}

    open fun openPlayerListMenu(player: Player) {}

    abstract val menuItemSlot: Int

    val settingLore: String
        get() {
            if (settingType == SettingType.IntegerSetting) {
                return ColorTranslator.translateColorCodes(
                    "&2Increase (left), &4Decrease (right)"
                )
            }

            if (settingType == SettingType.StringSetting) {
                return ColorTranslator.translateColorCodes("&3Click to edit")
            }

            if (settingType == SettingType.BooleanSetting) {
                return ColorTranslator.translateColorCodes("&3Click to toggle")
            }

            if (settingType == SettingType.PlayerBooleanSetting) {
                return ColorTranslator.translateColorCodes("&3Click to view/edit")
            }

            return ""
        }

    fun handleItemClick(event: InventoryClickEvent) {
        var max = 128
        var jump = 1

        // TODO: Remove this! Add max property to IntegerSetting!
        if (event.currentItem!!.type == Material.IRON_BARS) {
            max = 30000000
            jump = 50
        }

        if (!event.currentItem!!.displayName().equals("Time Between")) {
            max = 3600
        }

        if (settingType == SettingType.IntegerSetting) {
            val current = setting as Int
            if (event.isLeftClick && current != max) {
                this.setIntValue(current + jump)
            } else if (event.isRightClick && current >= 1) {
                this.setIntValue(current - jump)
            }

            return
        }

        if (settingType == SettingType.BooleanSetting) {
            if (!event.isLeftClick) return
            val current = setting as Boolean
            this.setBooleanValue(!current)

            return
        }

        if (settingType == SettingType.StringSetting) {
            if (!event.isLeftClick) return
            // Open sign, worry about this later
        }

        if (settingType == SettingType.PlayerBooleanSetting) {
            // On left click, open menu, then grab each player in the server
            // get their player head and display them with boolean values for now?
            if (event.isLeftClick) {
                openPlayerListMenu(event.whoClicked as Player)
                // MenuManager.openMenu(PlayerListMenu::class.java, event.whoClicked as Player)
            }
        }
    }
}
