package net.matees.arcade.deathswap

import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.arcade.Minigame
import net.matees.arcade.deathswap.DeathSwap
import net.matees.settings.SettingsMenu

class DeathSwapSettingsMenu(playerMenuUtility: PlayerMenuUtility?) : SettingsMenu(playerMenuUtility) {
    override fun getMenuName(): String {
        return "Death Swap Settings"
    }

    override val minigame: Minigame
        get() = DeathSwap.Companion.instance
}
