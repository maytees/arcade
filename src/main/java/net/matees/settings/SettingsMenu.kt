package net.matees.settings

import me.kodysimpson.simpapi.exceptions.MenuManagerException
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException
import me.kodysimpson.simpapi.menu.Menu
import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.arcade.Minigame
import org.bukkit.event.inventory.InventoryClickEvent

abstract class SettingsMenu(playerMenuUtility: PlayerMenuUtility?) : Menu(playerMenuUtility) {
    abstract val minigame: Minigame

    @Throws(MenuManagerNotSetupException::class, MenuManagerException::class)
    override fun handleMenu(inventoryClickEvent: InventoryClickEvent) {
        for (setting in minigame.settings) {
            if (setting.menuItem.isSimilar(inventoryClickEvent.currentItem)) {
                setting.handleItemClick(inventoryClickEvent)

                // Updates?
                inventory.clear()
                setting.menuItem = setting.menuItem
                this.setMenuItems()
            }
        }
    }

    override fun getSlots(): Int {
        return 27
    }

    override fun cancelAllClicks(): Boolean {
        return true
    }

    override fun setMenuItems() {
        for (setting in minigame.settings) {
            inventory.setItem(setting.menuItemSlot, setting.menuItem)
        }
    }
}
