package net.matees.menus

import me.kodysimpson.simpapi.exceptions.MenuManagerException
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException
import me.kodysimpson.simpapi.menu.Menu
import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.settings.*
import net.matees.settings.global.GlobalSettings
import org.bukkit.event.inventory.InventoryClickEvent

class GlobalSettingsMenu(playerMenuUtility: PlayerMenuUtility?) : Menu(playerMenuUtility) {
    override fun cancelAllClicks(): Boolean {
        return true
    }

    override fun getMenuName(): String {
        return "Global Settings"
    }

    override fun getSlots(): Int {
        return 54
    }

    @Throws(MenuManagerNotSetupException::class, MenuManagerException::class)
    override fun handleMenu(event: InventoryClickEvent) {
        for (s in GlobalSettings.Companion.instance.settings) {
            if (s is Global) {
                if (s.menuItem.isSimilar(event.currentItem)) {
                    s.handleItemClick(event)

                    // Weird
                    val setting = s as Global
                    setting.onChange()

                    inventory.clear()
                    //s.menuItem = s.menuItem
                    this.setMenuItems()
                }
            }
        }
    }

    override fun setMenuItems() {
        for (setting in GlobalSettings.Companion.instance.settings) {
            inventory.setItem(setting.menuItemSlot, setting.menuItem)
        }
    }
}
