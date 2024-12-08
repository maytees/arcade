package net.matees.arcade.blockshuffle

import me.kodysimpson.simpapi.menu.PlayerMenuUtility
import net.matees.arcade.Minigame
import net.matees.settings.AbstractSettingsMenu

class BlockShuffleSettingsMenu(playerMenuUtility: PlayerMenuUtility?) : AbstractSettingsMenu(playerMenuUtility) {
    override fun getMenuName(): String {
        return "Block Shuffle Settings"
    }

    override val minigame: Minigame
        get() = BlockShuffle.instance
}
