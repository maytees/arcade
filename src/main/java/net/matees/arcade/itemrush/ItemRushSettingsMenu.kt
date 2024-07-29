package net.matees.arcade.itemrush

import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.arcade.Minigame
import net.matees.settings.SettingsMenu

class ItemRushSettingsMenu(playerMenuUtility: PlayerMenuUtility?) : SettingsMenu(playerMenuUtility) {
    override fun getMenuName(): String {
        return "Item Rush Settings"
    }

    override val minigame: Minigame
        get() = ItemRush.Companion.instance
}
