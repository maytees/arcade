package net.matees.arcade.deathswap

import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.arcade.Minigame
import net.matees.settings.AbstractSettingsMenu

class DeathSwapSettingsMenu(playerMenuUtility: PlayerMenuUtility?) : AbstractSettingsMenu(playerMenuUtility) {
    override fun getMenuName(): String {
        return "Death Swap Settings"
    }

    override val minigame: Minigame
        get() = DeathSwap.Companion.instance
}
