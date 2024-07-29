package net.matees.arcade.manhunt

import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.arcade.Minigame
import net.matees.settings.SettingsMenu

class ManhuntSettingsMenu(playerMenuUtility: PlayerMenuUtility?) : SettingsMenu(playerMenuUtility) {
    override val minigame: Minigame
        get() = Manhunt.Companion.instance

    override fun getMenuName(): String {
        return "Manhunt Settings"
    }
}
