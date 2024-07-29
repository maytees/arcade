package net.matees.arcade.mobrush

import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.arcade.Minigame
import net.matees.settings.SettingsMenu

class MobRushSettingsMenu(playerMenuUtility: PlayerMenuUtility?) : SettingsMenu(playerMenuUtility) {
    override fun getMenuName(): String {
        return "Mob Rush Settings"
    }

    override val minigame: Minigame
        get() = MobRush.Companion.instance
}
