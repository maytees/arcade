package net.matees.arcade.itemrush

import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.arcade.Minigame
import net.matees.settings.AbstractSettingsMenu

class ItemRushSettingsMenu(playerMenuUtility: PlayerMenuUtility?) : AbstractSettingsMenu(playerMenuUtility) {
    override fun getMenuName(): String {
        return "Item Rush Settings"
    }

    override val minigame: Minigame
        get() = ItemRush.Companion.instance
}
