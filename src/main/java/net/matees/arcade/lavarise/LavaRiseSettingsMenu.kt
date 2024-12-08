package net.matees.arcade.lavarise

import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.arcade.Minigame
import net.matees.settings.AbstractSettingsMenu

class LavaRiseSettingsMenu(playerMenuUtility: PlayerMenuUtility?) : AbstractSettingsMenu(playerMenuUtility) {
    override val minigame: Minigame
        get() = LavaRise.Companion.instance

    override fun getMenuName(): String {
        return "Lava Rise Settings"
    }
}
